name := "demo"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.20"
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.2"
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2"
libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.30" % Test

