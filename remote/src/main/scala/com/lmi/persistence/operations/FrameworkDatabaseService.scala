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


class FrameworkDatabaseService extends Actor  with ActorLogging with SubscriberOperations {

  def receive = LoggingReceive {
    case dbmsg: Any =>
    	Subscriber(log,dbmsg)
  }
  
}
