# Slinky expo-sqlite-example
Refactoring of [Expo](https://expo.io)'s [sqlite-example](https://github.com/expo/sqlite-example) coded in [Scala](https://scala-lang.org/) through [Scala.js](https://www.scala-js.org), [Slinky](https://slinky.dev), [ScalablyTyped](https://github.com/oyvindberg/ScalablyTyped).

## Requirements
Make sure you have [sbt](https://www.scala-sbt.org) and [yarn](https://yarnpkg.com) installed.

### Install the Expo CLI
```sh
$ yarn global add expo-cli
```

## How to
### Run in development

First compile your Scala code to JavaScript by running:
```sh
$ sbt fastOptJS
```

Then, launch the app with Expo:
```sh
$ expo start
```

## Acknowledgements
`slinky-expo-sqlite-example` is based on Slinky's [Expo Scala Template](https://github.com/shadaj/expo-template-scala).

This demo uses the Scala.js typings developed by Øyvind Raddum Berg, see more demos at [SlinkyDemos](https://github.com/ScalablyTyped/SlinkyDemos).
