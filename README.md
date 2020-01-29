# Slinky expo-sqlite-example
Refactoring of [Expo](https://expo.io)'s [sqlite-example](https://github.com/expo/sqlite-example) coded in [Scala](https://scala-lang.org/) through [Scala.js](https://www.scala-js.org), [Slinky](https://slinky.dev), [ScalablyTyped](https://github.com/oyvindberg/ScalablyTyped).

## How to
### Build the app
Make sure you have [sbt](https://www.scala-sbt.org/) and [npm](https://www.npmjs.com/) installed.

First compile your Scala code to JavaScript by running:
```sh
$ sbt fastOptJS
```

Then, launch the app with Expo:
```sh
$ npm start
```

### See the app already published with Expo
Go to the [app Expo page](https://expo.io/@mcallisto/slinky-router-native-basic).

## Acknowledgements
`slinky-expo-sqlite-example` is based on Slinky's [Expo Scala Template](https://github.com/shadaj/expo-template-scala).

This demo uses the Scala.js typings developed by Øyvind Raddum Berg, see more demos at [SlinkyTypedDemos](https://github.com/ScalablyTyped/SlinkyTypedDemos).
