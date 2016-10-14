name := "play-java"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(

  "mysql" % "mysql-connector-java" % "5.1.40",
  cache,
  javaJdbc,
  cache,
  javaWs
)
