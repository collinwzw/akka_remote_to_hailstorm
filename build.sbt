val akkaVersion = "2.5.31"
val project = Project(
      id = "akka_remote_to_hailstorm",
      base = file(".")
).settings(
      name := """hailstorm""",
      scalaVersion := "2.12.11",
      libraryDependencies ++= Seq(
            "com.typesafe.akka" %% "akka-actor" % akkaVersion,
            "com.typesafe.akka" %% "akka-remote" % akkaVersion
      )
)