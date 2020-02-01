package hello.world

import org.scalajs.dom.console
import typings.expo.globalsWebMod.SQLite
import typings.expoSqlite.sqliteTypesMod.{SQLError, SQLResultSet, SQLTransaction}
import upickle.default.read

import scala.scalajs.js
import scala.scalajs.js.JSON

object Database {

  val db = SQLite.openDatabase("db.db")

  def createTable(): Unit = db.transaction(
    _.executeSql(
      "create table if not exists items (id integer primary key not null, done int, value text);"
    )
  )

  def insertItem(txt: String, onSuccess: () => Unit): Unit = db.transaction(
    callback = { tx: SQLTransaction =>
      tx.executeSql(
        "insert into items (done, value) values (0, ?)",
        js.Array(txt)
      )
      tx.executeSql(
        "select * from items",
        js.Array(),
        (_: SQLTransaction, set: SQLResultSet) => console.log(JSON.stringify(set))
      )
    },
    errorCallback = (_: SQLError) => (),
    successCallback = onSuccess
  )

  def updateItemWith(id: Int, onSuccess: () => Unit): Unit = db.transaction(
    callback = { tx: SQLTransaction =>
      tx.executeSql(
        "update items set done = 1 where id = ?;",
        js.Array(id)
      )
    },
    errorCallback = (_: SQLError) => (),
    successCallback = onSuccess
  )

  def deleteItemWith(id: Int, onSuccess: () => Unit): Unit = db.transaction(
    callback = { tx: SQLTransaction =>
      tx.executeSql(
        "delete from items where id = ?;",
        js.Array(id)
      )
    },
    errorCallback = (_: SQLError) => (),
    successCallback = onSuccess
  )

  def selectFilteredItems(done: Boolean, onFound: List[Item] => Unit): Unit = db.transaction(
    callback = { tx: SQLTransaction =>
      tx.executeSql(
        "select * from items where done = ?;",
        js.Array({ if (done) 1 else 0 }),
        callback = (_: SQLTransaction, set: SQLResultSet) => {
          val selectedItems = (0 until set.rows.length.toInt)
            .map(index => read[Item](ujson.read(JSON.stringify(set.rows.item(index.toDouble)))))
            .toList
          onFound(selectedItems)
        }
      )
    }
  )

}
