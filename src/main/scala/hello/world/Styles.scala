package hello.world

import typings.expoConstants.mod.Constants
import typings.reactNative.mod.{TextStyle, ViewStyle}
import typings.reactNative.reactNativeStrings.{bold, center, row}

object Styles {

  val container: ViewStyle = ViewStyle(
    backgroundColor = "#fff",
    flex = 1,
    paddingTop = 10 // todo how to use Constants
  )

  val heading: TextStyle = TextStyle(
    fontSize = 20,
    fontWeight = bold,
    textAlign = center
  )

  val flexRow: ViewStyle = ViewStyle(
    flexDirection = row
  )

  val input: TextStyle = TextStyle(
    borderColor = "#4630eb",
    borderRadius = 4,
    borderWidth = 1,
    flex = 1,
    height = 48,
    margin = 16,
    padding = 8
  )

  val listArea: ViewStyle = ViewStyle(
    backgroundColor = "#f0f0f0",
    flex = 1,
    paddingTop = 16
  )

  val sectionContainer: ViewStyle = ViewStyle(
    marginBottom = 16,
    marginHorizontal = 16
  )

  val sectionHeading: TextStyle = TextStyle(
    fontSize = 18,
    marginBottom = 8
  )

  val touchableDone: ViewStyle = ViewStyle(
    backgroundColor = "#1c9963", // "#fff",
    borderColor = "#000",
    borderWidth = 1,
    padding = 8
  )

  val touchableUndone: ViewStyle = ViewStyle(
    backgroundColor = "#fff",
    borderColor = "#000",
    borderWidth = 1,
    padding = 8
  )

  val touchableTextDone: TextStyle = TextStyle(
    color = "#fff"
  )

  val touchableTextUndone: TextStyle = TextStyle(
    color = "#000"
  )

}
