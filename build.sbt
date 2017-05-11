name := "vectors4s"
organization := "org.broadinstitute"

version := "0.1"

lazy val root = project in file(".")

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)

