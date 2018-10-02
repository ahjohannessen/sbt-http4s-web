package com
package github
package ahjohannessen
package h4w

import sbt._
import sbt.Keys._
import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.web.Import.Assets
import com.typesafe.sbt.web.Import.WebKeys.packagePrefix
import org.webjars.WebJarAssetLocator.WEBJARS_PATH_PREFIX

object Http4sWebPlugin extends AutoPlugin {

  val autoImport = Http4sWebKeys

  override def requires = SbtWeb

  import autoImport._

  override lazy val projectSettings: Seq[Setting[_]] = Seq(

    http4sWebAssetsDevTarget       := target.value / "h4w",
    http4sWebAssetsBaseDirName     := "public",
    http4sWebExcludeWebJarMappings := true,

    packagePrefix in Assets := s"${http4sWebAssetsBaseDirName.value}/",
    managedClasspath in Runtime += (packageBin in Assets).value,

    internalDependencyClasspath in Runtime ++= Seq(http4sWebAssetsDevTarget.value),
    internalDependencyClasspath in Runtime := (internalDependencyClasspath in Runtime).dependsOn(Def.task {
      IO.copy((mappings in Assets).value.map {
        case (f, p) ⇒ f -> (http4sWebAssetsDevTarget.value / http4sWebAssetsBaseDirName.value / p)
      })
    }).value,

    mappings in (Compile, packageBin) := (mappings in (Compile, packageBin)).value.filterNot {
      case (_, dest) ⇒ http4sWebExcludeWebJarMappings.value && dest.contains(WEBJARS_PATH_PREFIX)
    }

  )

}

object Http4sWebKeys {

  val http4sWebAssetsDevTarget       = settingKey[File]("Target directory for dev assets in runtime.")
  val http4sWebAssetsBaseDirName     = settingKey[String]("Top-level directory under dev assets, e.g. 'public'.")
  val http4sWebExcludeWebJarMappings = settingKey[Boolean]("Exclude webjar assets under 'META-INF/resources/webjars/' when packaging project jar.")

}