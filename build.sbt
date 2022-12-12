scalaVersion := "2.13.8"
val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.10"

enablePlugins(
  JavaAppPackaging,
  DockerPlugin
)
dockerExposedPorts := Seq(8080)
javaOptions in Universal ++= Seq("-J-Xmx64m", "-J-Xms64m", "-jvm-debug 12345")
Compile / mainClass := Some("AkkaHttpSimple")
Docker / packageName := "part2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.h2database" % "h2" % "2.1.214",
  "ch.qos.logback" % "logback-classic" % "1.4.5",
  "org.postgresql" % "postgresql" % "42.5.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
  "ch.megard" %% "akka-http-cors" % "1.1.3"
)
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "4.6.0"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion

// set the main class for 'sbt run'
mainClass in (Compile, run) := Some("AkkaHttpSimple")
// set the main class for 'sbt runLocal'
TaskKey[Unit]("runLocal") := (runMain in Compile)
  .toTask(" BasicHttp")
  .value
// set the main class for 'sbt initData'
TaskKey[Unit]("initDatabase") := (runMain in Compile)
  .toTask(" InitialDatabase")
  .value
