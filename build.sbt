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
  "-P:scalajs:sjsDefinedByDefault", // in preparation for scala.js 1.0.
  "-Ymacro-annotations"
)

scalaJSUseMainModuleInitializer := false

/* disabled because it somehow triggers many warnings */
emitSourceMaps := false

scalaJSModuleKind := ModuleKind.CommonJSModule

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"
libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.9.7"
libraryDependencies += "me.shadaj" %%% "slinky-native" % "0.6.3"
libraryDependencies += "me.shadaj" %%% "slinky-hot" % "0.6.3"

externalNpm := {
  Process("yarn", baseDirectory.value).!
  baseDirectory.value
}

Compile / stFlavour := Flavour.SlinkyNative
Compile / stIgnore += "csstype"
Compile / stMinimize := Selection.AllExcept("expo")
