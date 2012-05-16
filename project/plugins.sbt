resolvers += "Another Typesafe Repo" at "http://repo.akka.io/releases/"

resolvers += Classpaths.typesafeSnapshots

libraryDependencies += 
  Defaults.sbtPluginExtra( 
    "com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.0-SNAPSHOT", 
    "0.11.2", 
    "2.9.1" 
  )

addSbtPlugin("com.typesafe.startscript" % "xsbt-start-script-plugin" % "0.5.2")

resolvers += "spray repo" at "http://repo.spray.cc"

resolvers += "Typesafe" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("cc.spray" % "sbt-revolver" % "0.6.1")

resolvers += Resolver.url("sbt-plugin-releases",
  			 new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.1")
