name := "scalandra"

organization := "com.github.greenhost87"

version := "0.1.0"

scalaVersion := "2.11.8"

scalacOptions := Seq(
  "-feature",
  "-unchecked",
  "-deprecation",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
//  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture",
  "-Ywarn-unused-import",
  "-encoding",
  "UTF-8"
)

fork in Test := true

libraryDependencies ++= {
  Seq(
    "com.google.guava" % "guava" % "16.0.1",
    "org.slf4j" % "slf4j-api" % "1.7.12",
    "com.datastax.cassandra" % "cassandra-driver-core" % "3.6.0",
    "org.scala-lang" % "scala-reflect" % "2.11.6",
    "joda-time" % "joda-time" % "2.8.1",
    "org.joda" % "joda-convert" % "1.7",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % "test",
    "org.cassandraunit" % "cassandra-unit" % "3.5.0.1" % "test" excludeAll(
      ExclusionRule(organization = "org.slf4j", name = "*"),
      ExclusionRule(organization = "org.hectorclient", name = "hector-core"),
      ExclusionRule(organization = "com.datastax.cassandra", name = "cassandra-driver-core")
    )
  )
}