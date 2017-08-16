lazy val root = (project in file("."))
  .settings(
      ScriptedPlugin.projectSettings,
      scriptedLaunchOpts += ("-Dplugin.version=" + version.value),
      scriptedLaunchOpts ++= sys.process.javaVmArguments.filter(
          a => Seq("-Xmx", "-Xms", "-XX", "-Dsbt.log.noformat").exists(a.startsWith)
      ),
      sbtPlugin := true,
      name := "sbt-ghpages",
      organization := "com.typesafe.sbt",
      version := "0.6.3-SNAPSHOT",
      resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven",
      publishMavenStyle := true,
      libraryDependencies ++= Seq(
          Defaults.sbtPluginExtra(
              "com.typesafe.sbt" % "sbt-git" % "0.9.3",
              /*(sbtBinaryVersion in pluginCrossBuild).value,*/
              (scalaBinaryVersion in pluginCrossBuild).value
          ),
          Defaults.sbtPluginExtra(
              "com.typesafe.sbt" % "sbt-site" % "1.3.0",
              (sbtBinaryVersion in pluginCrossBuild).value,
              (scalaBinaryVersion in pluginCrossBuild).value
          )
      )
  )
