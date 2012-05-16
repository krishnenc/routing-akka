package com.lmi.server

import akka.actor.{ Actor, ActorRef, PoisonPill, ActorLogging, ActorSystem, Props, ReceiveTimeout }
import akka.actor.{ Address, AddressFromURIString }
import com.typesafe.config.ConfigFactory


object Framework {
  def main(args: Array[String]) {
    val terrabookSystem = ActorSystem("TerrabookApplication", ConfigFactory.load.getConfig("terrabook"))
    val ip = "127.0.0.1"
    val port = 2554
    val frameworkRouter = terrabookSystem.actorFor("akka://FrameworkApplication@%s:%s/user/framework_server".format(ip, port.toString()))
    for(i <- 1 to 10000) {
      frameworkRouter ! "Hello"
    }
  }
}



       
