scalaVersion := "2.13.8"
val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.10"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.h2database" % "h2" % "2.1.214",
  "ch.qos.logback" % "logback-classic" % "1.4.5",
  "org.postgresql" % "postgresql" % "42.5.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1"
)
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "4.6.0"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion

// set the main class for 'sbt run'
mainClass in (Compile, run) := Some("AkkaHttpSimple")
// set the main class for 'sbt initData'
TaskKey[Unit]("initDatabase") := (runMain in Compile)
  .toTask(" InitialDatabase")
  .value
