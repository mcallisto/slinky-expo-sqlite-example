package hello.world

import typings.expo.globalsWebMod.SQLite

object Database {

  val db = SQLite.openDatabase("db.db")

}
