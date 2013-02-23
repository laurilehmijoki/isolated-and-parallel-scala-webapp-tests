# Isolated and parallel webapp testing in Scala

The purpose of this project is to demonstrate how to test Scala web applications
in parallel.

Scalatra provides excellent means of parallel testing, but it can be a bit
tricky to set up a test infrastructure that takes advantage of Scalatra's
support for parallelism.

This project aims to give an example of how to test non-trivial web applications
efficiently.

## The greeting web application

This project contains a web application that knows how to greet.

On the command-line interface you can run it like this:

    ./sbt ~container:start

Start a new terminal window and call the application:

    curl localhost:8080 # You will be greeted

## The tests

Type in the console:

    ./sbt test

This will run two tests against `HelloServlet`. The interesting thing here is
that Scalatra will start two isolated Jetty instances, against which it will run
the tests in parallel.

In addition, each of the two Jetty instances are configured separately with
Guice.

## Conclusion

Combinations of production-environment configurations can be difficult to test
without proper tools. Furthermore, parallel web application tests come for free
in Scalatra, if you pay attention to your test infrastructure.

## Tools

* Scalatra
* Scala specs2
* Guice
* Embedded Jetty

## Credit

Thanks to Tomi Takussaari for giving the idea of how to run multiple Guice
injector instances side-by-side.
