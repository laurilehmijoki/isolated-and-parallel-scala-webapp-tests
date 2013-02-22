organization := "hello"

name := "world"

version := "0.0.1"

scalaVersion := "2.10.0"

resolvers += "Sonatype Nexus Releases" at "https://oss.sonatype.org/content/repositories/releases"

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.2.0",
  "org.scalatra" %% "scalatra-specs2" % "2.2.0" % "test",
  "org.sonatype.sisu" % "sisu-guice" % "3.1.2",
  "org.sonatype.sisu.inject" % "guice-servlet" % "3.1.2",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container,compile",
  "org.eclipse.jetty" % "jetty-server" % "8.1.7.v20120910" % "compile",
  "com.sun.jersey" % "jersey-core" % "1.11",
  "com.sun.jersey" % "jersey-client" % "1.11",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,compile" artifacts Artifact( "javax.servlet", "jar", "jar")
)

seq(webSettings :_*)
