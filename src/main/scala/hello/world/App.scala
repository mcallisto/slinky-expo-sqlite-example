package hello.world

import org.scalajs.dom.console

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.native._

import typings.expoSqlite.sqliteTypesMod.{SQLError, SQLResultSet, SQLTransaction}

import scala.scalajs.js
import scala.scalajs.js.JSON

@react object App {

  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    val (text, updateText) = useState("")
    val (counter, updateCounter) = useState(0)

    useEffect(
      () =>
        Database.db.transaction(
          _.executeSql(
            "create table if not exists items (id integer primary key not null, done int, value text);"
          )
        ),
      Seq() // run an effect and clean it up only once (on mount and unmount)
    )

    def add(txt: String): Unit =
      if (txt.nonEmpty)
        Database.db.transaction(
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
          successCallback = () => update()
        )

    def update(): Unit = updateCounter(counter + 1)

    View(style = Styles.container)(
      Text(style = Styles.heading)("SQLite Example"),
      View(style = Styles.flexRow)(
        TextInput(
          onChangeText = (txt: String) => updateText(txt),
          onSubmitEditing = { () =>
            add(text)
            updateText("")
          },
          placeholder = "what do you need to do?",
          style = Styles.input,
          value = text
        )
      ),
      ScrollView()(
        Items(
          done = false,
          onPressItem = (id: Int) =>
            Database.db.transaction(
              callback = { tx: SQLTransaction =>
                tx.executeSql(
                  "update items set done = 1 where id = ?;",
                  js.Array(id)
                )
              },
              errorCallback = (_: SQLError) => (),
              successCallback = () => update()
            ),
          counter = counter
        ),
        Items(
          done = true,
          onPressItem = (id: Int) =>
            Database.db.transaction(
              callback = { tx: SQLTransaction =>
                tx.executeSql(
                  "delete from items where id = ?;",
                  js.Array(id)
                )
              },
              errorCallback = (_: SQLError) => (),
              successCallback = () => update()
            ),
          counter = counter
        )
      )
    )

  }
}
