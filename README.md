routing-akka
============

A demo project to reproduce an issue i was having on akka

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
used with a Balancing Dispatcher. This solves the problem.

https://groups.google.com/forum/?fromgroups#!topic/akka-user/WHH0JQd4SIU
