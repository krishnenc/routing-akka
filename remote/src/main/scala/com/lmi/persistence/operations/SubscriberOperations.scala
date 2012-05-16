package com.lmi.persistence.operations

import akka.actor._
import akka.event.LoggingReceive

trait SubscriberOperations {

  def Subscriber(log: akka.event.LoggingAdapter,
		 db: Any) {

    db match {
      case "Hello" =>
        log.info("Got Message")
   }

}

}
