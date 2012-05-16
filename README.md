routing-akka
============

A demo project to reproduce a bug in akka

Fire two terminals

sbt

project remote

run

----

sbt

project local

run

An assertion error should be thrown in project remote

N.B According to Viktor Klang a SmallestMailbox router should not be
used with a Balancing Dispatcher

https://groups.google.com/forum/?fromgroups#!topic/akka-user/WHH0JQd4SIU
