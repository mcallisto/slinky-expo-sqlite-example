package hello.world

import scala.scalajs.js.Dynamic.literal
import typings.expoConstants.mod.Constants

object Styles {

  val container = literal(
    backgroundColor = "#fff",
    flex = 1,
    paddingTop = 10 // todo how to use Constants
  )

  val heading = literal(
    fontSize = 20,
    fontWeight = "bold",
    textAlign = "center"
  )

  val flexRow = literal(
    flexDirection = "row"
  )

  val input = literal(
    borderColor = "#4630eb",
    borderRadius = 4,
    borderWidth = 1,
    flex = 1,
    height = 48,
    margin = 16,
    padding = 8
  )

  val listArea = literal(
    backgroundColor = "#f0f0f0",
    flex = 1,
    paddingTop = 16
  )

  val sectionContainer = literal(
    marginBottom = 16,
    marginHorizontal = 16
  )

  val sectionHeading = literal(
    fontSize = 18,
    marginBottom = 8
  )

  val touchableDone = literal(
    backgroundColor = "#1c9963", // "#fff",
    borderColor = "#000",
    borderWidth = 1,
    padding = 8
  )

  val touchableUndone = literal(
    backgroundColor = "#fff",
    borderColor = "#000",
    borderWidth = 1,
    padding = 8
  )

  val touchableTextDone = literal(
    color = "#fff"
  )

  val touchableTextUndone = literal(
    color = "#000"
  )

}
