# micronaut-pf4j-poc

This is a POC project for micronaut intergrate with pf4j.

## Requirements

- Java 11

## How to run

```sh
$ ./gradlew shadowJar
$ mkdir -p build/libs/plugins
$ cp -fv plugins/dummyImpl/build/libs/dummy-impl-0.0.1.jar plugins/fooImpl/build/libs/foo-impl-0.0.1.jar build/libs/plugins/
$ cd build/libs/
$ java -jar micronaut-pf4j-poc-0.1-all.jar
 __  __ _                                  _
|  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_
| |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
| |  | | | (__| | | (_) | | | | (_| | |_| | |_
|_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
  Micronaut (v3.0.1)

01:14:08.528 [scheduled-executor-thread-1] INFO  org.pf4j.DefaultPluginStatusProvider - Enabled plugins: []
01:14:08.530 [scheduled-executor-thread-1] INFO  org.pf4j.DefaultPluginStatusProvider - Disabled plugins: []
01:14:08.532 [scheduled-executor-thread-1] INFO  org.pf4j.DefaultPluginManager - PF4J version 0.1 in 'deployment' mode
01:14:08.611 [main] INFO  io.undertow - starting server: Undertow - 2.2.9.Final
01:14:08.615 [main] INFO  org.xnio - XNIO version 3.8.4.Final
01:14:08.621 [main] INFO  org.xnio.nio - XNIO NIO Implementation Version 3.8.4.Final
01:14:08.723 [main] INFO  org.jboss.threads - JBoss Threads version 3.1.0.Final
01:14:08.767 [main] INFO  org.pf4j.AbstractPluginManager - Plugin 'DummyPluginImpl1@0.0.1' resolved
01:14:08.768 [main] INFO  org.pf4j.AbstractPluginManager - Plugin 'FooPluginImpl1@0.0.1' resolved
01:14:08.768 [main] INFO  org.pf4j.AbstractPluginManager - Start plugin 'DummyPluginImpl1@0.0.1'
01:14:08.768 [main] INFO  org.pf4j.AbstractPluginManager - Start plugin 'FooPluginImpl1@0.0.1'
01:14:08.771 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 770ms. Server Running: http://[0:0:0:0:0:0:0:0]:8080
```

Open another terminal:

```sh
$ curl -s localhost:8080/plugin1
{"response":"Response from DummyImpl","plugin":"DummyPlugin"}

$ curl -s localhost:8080/plugin2
{"response":"Response from FooImpl","plugin":"FooPlugin"}
```
