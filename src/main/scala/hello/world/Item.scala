package hello.world

import upickle.default.{ReadWriter => RW, macroRW}

case class Item(id: Int, done: Int, value: String)

object Item {
  implicit val rw: RW[Item] = macroRW
}
