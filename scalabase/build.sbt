name := "scala-base"
version := "1.0"
libraryDependencies ++= Seq(
  "com.xiaomi" %% "ai-api-common" % "1.1.534"
)
dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
