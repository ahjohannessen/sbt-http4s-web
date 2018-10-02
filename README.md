[![License](https://img.shields.io/github/license/DavidGregory084/sbt-tpolecat.svg)](https://opensource.org/licenses/Apache-2.0)
[ ![Download](https://api.bintray.com/packages/ahjohannessen/sbt-plugins/sbt-http4s-web/images/download.svg) ](https://bintray.com/ahjohannessen/sbt-plugins/sbt-http4s-web/_latestVersion)
## sbt-http4s-web

sbt plugin that makes it less convoluted to use [sbt-web](https://github.com/sbt/sbt-web) and [http4s](https://http4s.org) together. 
It makes it such that assets are placed in paths that http4s is able to locate out of the box during development and production.

The plugin does this by tweaking a project's `Runtime / managedClasspath`, `Runtime / internalDependencyClasspath`, `Assets / packagePrefix`
and `Compile / packageBin / mappings`.

### Usage

Add the following to your project's project/plugins.sbt:
```
// replace x.y.z with value from version badge above.

addSbtPlugin("com.github.ahjohannessen" % "sbt-http4s-web" % "x.y.z")
```
and enable the plugin in your project's build:
```
.enablePlugins(Http4sWebPlugin)
```

### Settings

The settings below are available for configuration. The following lists available 
setting keys and their default values:
```scala
import sbt._
import sbt.Keys._
import com.github.ahjohannessen.h4w.Http4sWebKeys._

/*
* Directory assets are placed during development.
* */

http4sWebAssetsDevTarget := target.value / "h4w"

/*
* Top-level directory under assets and that is used as package prefix.
* */

http4sWebAssetsBaseDirName := "public"

/*
* Exclude webjar assets under 'META-INF/resources/webjars/' when packaging project jar.
* */

http4sWebExcludeWebJarMappings := true
```

### Tip
[sbt-revolver](https://github.com/spray/sbt-revolver) works great in conjunction with this plugin for fast development turnaround.

### License
`sbt-http4s-web` is licensed under [APL 2.0](http://www.apache.org/licenses/LICENSE-2.0)
