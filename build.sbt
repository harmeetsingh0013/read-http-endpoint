name := "stream-endpoint"

version := "1.0"

scalaVersion := "2.12.1"

val akkaVersion = "2.5.2"
val http4sVersion = "0.15.13"
val circeVersion = "0.6.1"

libraryDependencies := Seq(
  "org.http4s" %% "http4s-argonaut" % http4sVersion,
  "com.github.alexarchambault" %% "argonaut-shapeless_6.2" % "1.2.0-M4",
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion

)