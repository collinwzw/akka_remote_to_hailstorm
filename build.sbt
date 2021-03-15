val akkaVersion = "2.5.31"
scalaVersion := "2.12.11"
libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-remote" % akkaVersion
)