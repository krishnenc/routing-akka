package com.lmi.server

import com.typesafe.config.ConfigFactory
import akka.actor.{ ActorRef, Props, Actor, ActorSystem, ActorLogging }
import akka.routing.SmallestMailboxRouter
import akka.kernel.Bootable
import akka.routing.RemoteRouterConfig
import akka.actor.{ Address, AddressFromURIString }
import akka.routing.{ RemoteRouterConfig, DefaultResizer }

import com.lmi.persistence.operations.FrameworkDatabaseService
import com.lmi.persistence.operations.DbRouter

object Bootstrap extends App {

  val config = ConfigFactory.load()

  val frameworkSystem = ActorSystem("FrameworkApplication", ConfigFactory.load.getConfig("framework"))

  val frameworkAddress = Seq(Address("akka", "FrameworkApplication",
    config.getString("framework.ip-address"),
    config.getInt("framework.akka.remote.netty.port")))

  //TODO : Potential Bug in Router Resizing ReTest
  frameworkSystem.actorOf(Props[FrameworkActor].withRouter(
    RemoteRouterConfig(SmallestMailboxRouter(resizer = Some(DefaultResizer(
      lowerBound = 1, upperBound = 1000)), routerDispatcher = "framework-dispatcher"),
      frameworkAddress)),
    "framework_server")
  val framework_db = Props[FrameworkDatabaseService]
  DbRouter.Init(frameworkSystem, framework_db, "framework_db", "framework", "framework-dispatcher")

}

class FrameworkActor extends Actor with ActorLogging {
  import Bootstrap._
  def receive = {
    case db: String =>
      try {
        println("Database Message " + db)
        DbRouter.dbRouter forward db
        println("Router " + DbRouter.dbRouter)
      } catch {
        case e: Exception =>
          println("ERROR")
      }
  }
}

