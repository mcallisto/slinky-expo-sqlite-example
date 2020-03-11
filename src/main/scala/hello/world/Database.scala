package hello.world

import org.scalajs.dom.console
import typings.expo.globalsWebMod.SQLite
import typings.expoSqlite.sqliteTypesMod.{SQLError, SQLResultSet, SQLTransaction}
import upickle.default.read

import scala.scalajs.js
import scala.scalajs.js.JSON

object Database {

  val db = SQLite.openDatabase("db.db")

  private val tableName = "items"
  private val idColumn = "id"
  private val doneColumn = "done"
  private val valueColumn = "value"

  def createTable(): Unit = db.transaction(
    _.executeSql(
      s"create table if not exists $tableName ($idColumn integer primary key not null, $doneColumn int, $valueColumn text);"
    )
  )

  def insertItem(txt: String, onSuccess: () => Unit): Unit = db.transaction(
    callback = { tx: SQLTransaction =>
      tx.executeSql(
        s"insert into $tableName ($doneColumn, $valueColumn) values (0, ?)",
        js.Array(txt)
      )
      tx.executeSql(
        s"select * from $tableName",
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
        s"update $tableName set $doneColumn = 1 where $idColumn = ?;",
        js.Array(id)
      )
    },
    errorCallback = (_: SQLError) => (),
    successCallback = onSuccess
  )

  def deleteItemWith(id: Int, onSuccess: () => Unit): Unit = db.transaction(
    callback = { tx: SQLTransaction =>
      tx.executeSql(
        s"delete from $tableName where $idColumn = ?;",
        js.Array(id)
      )
    },
    errorCallback = (_: SQLError) => (),
    successCallback = onSuccess
  )

  def selectFilteredItems(done: Boolean, onFound: List[Item] => Unit): Unit = db.transaction(
    callback = { tx: SQLTransaction =>
      tx.executeSql(
        s"select * from $tableName where $doneColumn = ?;",
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
