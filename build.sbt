// Common settings

sbtPlugin := true
scalaVersion := "2.12.8"

name := "sbt-http4s-web"
description := "sbt-web for http4s"
organization := "com.github.ahjohannessen"
version := "0.0.6"
// Dependencies

addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.4.4")

// Publishing

bintrayOrganization in bintray := None
bintrayRepository := "sbt-plugins"
bintrayPackageLabels := Seq("sbt", "sbt-plugin", "sbt-http4s-web")
bintrayVcsUrl := Some("git@github.com:ahjohannessen/sbt-http4s-web.git")
publishMavenStyle := false
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))