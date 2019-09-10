import scalariform.formatter.preferences._

val akkaVersion = "2.5.25"
val redisScalaVersion = "1.9.0"
val scalaCollectionCompatVersion = "2.1.2"

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishArtifact in Test := false,
  // The Nexus repo we're publishing to.
//  publishTo := (version { (v: String) =>
//    val nexus = "https://oss.sonatype.org/"
//      if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
//      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
//  }).value,
  pomIncludeRepository := { x => false },
  pomExtra := (
    <developers>
      <developer>
        <id>ezra-quemuel</id>
        <name>Ezra Quemuel</name>
        <email>ezra.quemuel@gmail.com</email>
      </developer>
    </developers>
    <ciManagement>
        <system>travis</system>
        <url>https://travis-ci.org/ezra-quemuel/akka-persistence-redis</url>
      </ciManagement>
      <issueManagement>
        <system>github</system>
        <url>https://github.com/ezra-quemuel/akka-persistence-redis/issues</url>
      </issueManagement>
    )
  )

lazy val siteSettings = Seq(
  ghpagesNoJekyll := true,
  git.remoteRepo := scmInfo.value.get.connection)

lazy val dependencies = Seq(
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,
  "com.github.etaty" %% "rediscala" % redisScalaVersion,
  "org.scala-lang.modules" %% "scala-collection-compat" % scalaCollectionCompatVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % "test",
  "com.typesafe.akka" %% "akka-persistence-tck" % akkaVersion % "test",
  "com.github.xuwei-k" %% "scodec-msgpack" % "0.7.0" % "test")

lazy val root = project.in(file("."))
  .enablePlugins(SiteScaladocPlugin, GhpagesPlugin)
  .settings(publishSettings: _*)
  .settings(siteSettings: _*)
  .settings(
    resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
    organization := "com.ezra-quemuel",
    name := "akka-persistence-redis",
    version := "0.4.1",
    licenses += ("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    homepage := Some(url("https://github.com/ezra-quemuel/akka-persistence-redis")),
    scmInfo := Some(ScmInfo(url("https://github.com/ezra-quemuel/akka-persistence-redis"), "git@github.com:ezra-quemuel/akka-persistence-redis.git")),
    scalaVersion := "2.13.0",
    crossScalaVersions := Seq("2.13.0", "2.12.9", "2.11.12"),
    libraryDependencies ++= dependencies,
    parallelExecution in Test := false,
    scalacOptions in (Compile,doc) ++= Seq("-groups", "-implicits", "-implicits-show-all", "-diagrams", "-doc-title", "Akka Persistence Redis", "-doc-version", version.value, "-doc-footer", "Copyright © 2017 Safety Data"),
    autoAPIMappings := true,
    scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked"))
  .settings(
    scalariformPreferences := {
    scalariformPreferences.value
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(DoubleIndentConstructorArguments, true)
      .setPreference(MultilineScaladocCommentsStartOnFirstLine, true)
    })
