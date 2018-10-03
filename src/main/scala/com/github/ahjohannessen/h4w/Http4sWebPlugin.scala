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
    http4sWebPackageAssets         := sys.props.get("idea.managed").isEmpty,

    packagePrefix in Assets := s"${http4sWebAssetsBaseDirName.value}/",

    managedClasspath in Runtime ++= packageAssets.value,

    internalDependencyClasspath in Runtime += http4sWebAssetsDevTarget.value,
    internalDependencyClasspath in Runtime := (internalDependencyClasspath in Runtime).dependsOn(copyAssets).value,

    mappings in (Compile, packageBin) := (mappings in (Compile, packageBin)).value.filterNot {
      case (_, dest) ⇒ http4sWebExcludeWebJarMappings.value && dest.contains(WEBJARS_PATH_PREFIX)
    }

  )

  private lazy val copyAssets = Def.task[Unit] {
    IO.copy((mappings in Assets).value.map {
      case (f, p) ⇒ f -> (http4sWebAssetsDevTarget.value / http4sWebAssetsBaseDirName.value / p)
    })
  }

  private lazy val packageAssets = Def.taskDyn[Def.Classpath] {
    if (http4sWebPackageAssets.value) Def.task { Seq((packageBin in Assets).value).classpath } else Def.task { Nil }
  }

}