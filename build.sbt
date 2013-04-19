name := "RabbitMQ Chat"

version := "1.0.0"

organization := "net.liftweb.cookbook.chat"

scalaVersion := "2.9.2"

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                "releases" at "http://oss.sonatype.org/content/repositories/releases")

seq(com.github.siasia.WebPlugin.webSettings :_*)

net.virtualvoid.sbt.graph.Plugin.graphSettings

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.5-RC5"
  Seq(
    "net.liftweb"       %% "lift-webkit"        % liftVersion        % "compile",
    "net.liftmodules"   %% "lift-jquery-module_2.5" % "2.3",
    "net.liftmodules"   %% "amqp_2.5" % "1.3-SNAPSHOT",
    "org.eclipse.jetty" % "jetty-webapp"        % "8.1.7.v20120910"  % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"    % "logback-classic"     % "1.0.6",
    "org.specs2"        %% "specs2"             % "1.12.1"           % "test"
  )
}

