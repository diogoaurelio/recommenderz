name := """recommenderz"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

//val akkaVersion = "2.3.11"
val sparkVersion = "1.6.2"
val webJars = "2.1.4"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.webjars" % "jquery" % webJars,
  "org.webjars" % "bootstrap" % "3.3.5",
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.0.0",
  "org.webjars" %% "webjars-play" % "2.4.0",
  //"com.adrianhurt" %% "play-bootstrap3" % "0.4.4-P24",
  "org.webjars" % "bootstrap" % "3.1.1",
  "org.webjars" % "html5shiv" % "3.7.0",
  "org.webjars" % "bootstrap-datepicker" % "1.4.0",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.mohiva" %% "play-silhouette-password-bcrypt" % "4.0.0-RC1",
  "com.amazonaws" % "aws-java-sdk" % "1.10.20",
  "com.sksamuel.elastic4s" %% "elastic4s" % "1.4.14",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",

  //"com.typesafe.akka" %% "akka-actor"              % akkaVersion, // Akka Actor
  //"com.typesafe.akka" %% "akka-slf4j"              % akkaVersion, // Akka SLF4J
  "org.apache.spark"  %% "spark-core"              % sparkVersion,
  "org.apache.spark"  %% "spark-sql"               % sparkVersion, // Spark Dataframes
  "org.apache.spark"  %% "spark-mllib"             % sparkVersion // Spark MLLIB
)

//resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "jbcrypt repo" at "http://mvnrepository.com/",
  "Atlassian Releases" at "https://maven.atlassian.com/public/"
)