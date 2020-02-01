package hello.world

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.native._

@react object Items {

  case class Props(done: Boolean, onPressItem: Int => Unit, counter: Int)

  val component = FunctionalComponent[Props] { props =>
    val (items, updateItems) = useState(Nil: List[Item])

    useEffect(
      () => update(),
      Seq(props.counter) // run effect every time it changes
    )

    def update(): Unit =
      Database.selectFilteredItems(props.done, (found: List[Item]) => updateItems(found))

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
