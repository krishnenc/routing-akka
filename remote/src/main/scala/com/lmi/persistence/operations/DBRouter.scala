package com.lmi.persistence.operations

import akka.actor._
import akka.actor.SupervisorStrategy._
import akka.util.duration._
import akka.util.Duration
import akka.util.Timeout
import akka.event.LoggingReceive
import akka.pattern.{ ask, pipe }
import com.typesafe.config.ConfigFactory
import akka.dispatch.Dispatchers
import akka.routing.{ SmallestMailboxRouter, DefaultResizer }
import akka.actor.Props

object DbRouter {
  var dbRouter: ActorRef = _
  def Init(sys: akka.actor.ActorSystem, 
		   dbprops: Props, name: String, 
		   dbname : String , balancingDispatch : String) {
    dbRouter = sys.actorOf(Props(new DbRouter(dbprops, name))
    		  .withRouter(SmallestMailboxRouter(resizer = Some(DefaultResizer(
    	      lowerBound = 1, upperBound = 100)),routerDispatcher = balancingDispatch)))
  }
}
/**
 * DBRouter is started together with the actorSystem, it sends db messages to the service that manages the database
 */
class DbRouter(dbprops: Props, actorname: String) extends Actor with ActorLogging {
 
  val dbService = context.actorOf(dbprops, name = actorname)
  def receive = LoggingReceive {
    case msg: Any =>
      dbService forward msg
  }
}
