version := "0.1"
val commonsettings = Seq(
  scalaVersion := "2.13.1",
  scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-unchecked",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-Xfatal-warnings"
  ),
  libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.1" % "test"
)

lazy val root = (project in file("."))
  .aggregate(answers)
  .settings(commonsettings)
  .settings(
    name := "Hands-on Functional Programming in Scala"
  )

lazy val answers = (project in file("answers"))
  .settings(commonsettings)
  .settings(
    name := "answers to the exercises",
    Test / parallelExecution := false
  )

