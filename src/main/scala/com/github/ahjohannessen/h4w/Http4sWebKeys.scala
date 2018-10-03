package com
package github
package ahjohannessen
package h4w

import sbt._

object Http4sWebKeys {

  val http4sWebAssetsDevTarget       = settingKey[File]("Target directory for dev assets in runtime.")
  val http4sWebAssetsBaseDirName     = settingKey[String]("Top-level directory under dev assets, e.g. 'public'.")
  val http4sWebPackageAssets         = settingKey[Boolean]("Prevent `packageBin in Assets` from being run and added to `managedClasspath in Runtime`.")
  val http4sWebExcludeWebJarMappings = settingKey[Boolean]("Exclude webjar assets under 'META-INF/resources/webjars/' when packaging project jar.")

}