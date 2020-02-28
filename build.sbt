import scala.sys.process.Process

enablePlugins(ScalaJSPlugin, ScalablyTypedConverterExternalNpmPlugin)

name := "app"

scalaVersion := "2.13.1"

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-encoding",
  "utf-8", // Specify character encoding used by source files.
  "-explaintypes", // Explain type errors in more detail.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Ymacro-annotations"
)

scalaJSUseMainModuleInitializer := false

scalaJSLinkerConfig ~= (/* disabled because it somehow triggers many warnings */
  _.withSourceMap(false)
    .withModuleKind(ModuleKind.CommonJSModule))

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.0.0"
libraryDependencies += "com.lihaoyi" %%% "upickle" % "1.0.0"
libraryDependencies += "me.shadaj" %%% "slinky-native" % "0.6.4"
libraryDependencies += "me.shadaj" %%% "slinky-hot" % "0.6.4"

externalNpm := {
  Process("yarn", baseDirectory.value).!
  baseDirectory.value
}

stFlavour := Flavour.SlinkyNative
stIgnore += "csstype"
