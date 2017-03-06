name := """recommenderz"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

//val akkaVersion = "2.3.11"
val sparkVersion = "1.6.2"
val webJars = "2.1.4"

// base ones
libraryDependencies ++= Seq(
  //jdbc, // commented because play evolutions was conflicting -> http://stackoverflow.com/questions/33004303/a-binding-to-play-api-db-dbapi-was-already-configured-evolutions-and-injector-e
  cache,
  filters,
  ws,
  specs2 % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

// pretty shit
libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.0.0"
)

// Stuff
libraryDependencies ++=Seq(
  "com.amazonaws" % "aws-java-sdk" % "1.10.20"
)

// DBs
libraryDependencies ++= Seq(
  //"com.h2database" % "h2" % "1.4.188",
  //"org.postgresql" % "postgresql" % "9.4-1200-jdbc41",
  //"org.postgresql" % "postgresql" % "9.3-1100-jdbc41",
  //"org.postgresql" % "postgresql" % "9.4.1208",

  "mysql" % "mysql-connector-java" % "5.1.34"
  ,"com.typesafe.play" %% "play-slick" % "2.0.0"
  ,"com.typesafe.play" %% "play-slick-evolutions" % "2.0.0"
  ,"com.sksamuel.elastic4s" %% "elastic4s" % "1.4.14"
)

// Authentication - TODO
/*
// check this example: https://github.com/vahana-team/play-slick-silhouette-postgres
libraryDependencies ++= Seq(
  "com.mohiva" %% "play-silhouette" % "4.0.0-BETA4",
  "com.mohiva" %% "play-silhouette-password-bcrypt" % "4.0.0-BETA4",
  "com.mohiva" %% "play-silhouette-persistence-memory" % "4.0.0-BETA4",
  "com.mohiva" %% "play-silhouette-testkit" % "4.0.0-BETA4" % "test",
)
*/

// Machine Learning
libraryDependencies ++= Seq(
  //"com.typesafe.akka" %% "akka-actor"              % akkaVersion, // Akka Actor
  //"com.typesafe.akka" %% "akka-slf4j"              % akkaVersion, // Akka SLF4J
  //"org.apache.spark"  %% "spark-core"              % sparkVersion,
  //"org.apache.spark"  %% "spark-sql"               % sparkVersion, // Spark Dataframes
  //"org.apache.spark"  %% "spark-mllib"             % sparkVersion // Spark MLLIB
)


resolvers ++= Seq(
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "jbcrypt repo" at "http://mvnrepository.com/",
  "Atlassian Releases" at "https://maven.atlassian.com/public/"
)