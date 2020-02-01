package hello.world

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.native._

@react object App {

  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    val (text, updateText) = useState("")
    val (counter, updateCounter) = useState(0)

    useEffect(
      () => Database.createTable(),
      Seq() // run an effect and clean it up only once (on mount and unmount)
    )

    def add(txt: String): Unit =
      if (txt.nonEmpty)
        Database.insertItem(txt, () => update())

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
          onPressItem = (id: Int) => Database.updateItemWith(id, () => update()),
          counter = counter
        ),
        Items(
          done = true,
          onPressItem = (id: Int) => Database.deleteItemWith(id, () => update()),
          counter = counter
        )
      )
    )

  }
}
