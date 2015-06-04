name:="hello"

scalaVersion:="2.10.4"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  jdbc,
  anorm
)

transitiveClassifiers := Seq("sources")
