package hello.world

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.native._

import typings.expoSqlite.sqliteTypesMod.{SQLError, SQLResultSet, SQLTransaction}

import upickle.default.read

import scala.scalajs.js
import scala.scalajs.js.JSON

@react object Items {

  case class Props(done: Boolean, onPressItem: Int => Unit, counter: Int)

  val component = FunctionalComponent[Props] { props =>
    val (items, updateItems) = useState(Nil: List[Item])

    useEffect(
      () => update(),
      Seq(props.counter) // run effect every time it changes
    )

    def update(): Unit =
      Database.db.transaction(
        callback = { tx: SQLTransaction =>
          tx.executeSql(
            "select * from items where done = ?;",
            js.Array({ if (props.done) 1 else 0 }),
            callback = (_: SQLTransaction, set: SQLResultSet) => {
              val selectedItems = (0 until set.rows.length.toInt)
                .map(index => read[Item](ujson.read(JSON.stringify(set.rows.item(index.toDouble)))))
                .toList
              updateItems(selectedItems)
            }
          )
        },
        errorCallback = (_: SQLError) => ()
      )

    View(style = Styles.sectionContainer)(
      Text(style = Styles.sectionHeading)(if (props.done) "Completed" else "Todo"),
      items.map({ item =>
        TouchableOpacity(
          onPress = () => props.onPressItem(item.id),
          style = if (item.done == 1) Styles.touchableDone else Styles.touchableUndone
        ).withKey("item" + item.id)(
          Text(
            style = if (item.done == 1) Styles.touchableTextDone else Styles.touchableTextUndone
          )(item.value)
        )
      })
    )

  }
}
