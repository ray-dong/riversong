name := "My Project"

version := "1.0"

scalaVersion := "2.9.1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0-M1"

libraryDependencies += "com.typesafe.akka" % "akka-slf4j" % "2.0-M1"

libraryDependencies += "com.typesafe.akka" % "akka-kernel" % "2.0-M1"

libraryDependencies += "com.typesafe.akka" % "akka-remote" % "2.0-M1"

libraryDependencies += "com.googlecode.disruptor" % "disruptor" % "2.7.1"

libraryDependencies += "com.espertech" % "esper" % "4.4.0"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"

libraryDependencies += "org.slf4j" % "log4j-over-slf4j" % "1.6.4"

libraryDependencies += "org.neo4j" % "neo4j" % "1.6.M02"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "6.0.3"

libraryDependencies += "org.scalaz" %% "scalaz-http" % "6.0.3"

libraryDependencies += "net.databinder" %% "unfiltered-filter" % "0.5.3"

libraryDependencies += "net.databinder" %% "unfiltered-jetty" % "0.5.3"

