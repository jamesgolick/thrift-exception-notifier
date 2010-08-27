import sbt._

class TimelineServiceProject(info: ProjectInfo) extends DefaultProject(info) {
  val liftUtil = "net.liftweb" % "lift-util_2.8.0" % "2.1-M1" withSources()
  val logula   = "com.codahale" %% "logula" % "1.0.3" withSources()
}
