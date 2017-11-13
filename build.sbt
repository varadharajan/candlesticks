import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "in.varadharajan",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "candlesticks",
    libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.18.0",

    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
  )
