package hello.world

import slinky.core.ReactComponentClass
import slinky.hot
import slinky.native.AppRegistry

import scala.scalajs.js
import scala.scalajs.LinkingInfo
import scala.scalajs.js.annotation.JSExportTopLevel

object Main {
  //@todo commented out for the time being, is a bug in Slinky 0.6.4
//  if (LinkingInfo.developmentMode) {
//    hot.initialize()
//  }

  @JSExportTopLevel("app")
  val app: ReactComponentClass[_] = App.component
}
