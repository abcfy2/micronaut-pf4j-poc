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
$ curl -s "localhost:8080?plugin=plugin1"
{"response":"Response from DummyImpl, provided by plugin: 'PluginDescriptor [pluginId=DummyPluginImpl1, pluginClass=micronaut.pf4j.poc.plugin.impl.DummyPlugin, version=0.0.1, provider=null, dependencies=[], description=This is my first dummy plugin, requires=*, license=null]'","plugin":"DummyPlugin"}

$ curl -s "localhost:8080?plugin=plugin2"
{"response":"Response from FooImpl, provided by plugin: 'PluginDescriptor [pluginId=FooPluginImpl1, pluginClass=micronaut.pf4j.poc.plugin.impl.FooPlugin, version=0.0.1, provider=null, dependencies=[], description=This is my first foo plugin, requires=*, license=null]'","plugin":"FooPlugin"}
```

## Online update

Follow the steps like [abcfy2/pf4j-poc#online-update](https://github.com/abcfy2/pf4j-poc#online-update), create `updates/`, `repositories.json` and `plugins.json`. And the running output should be like this:

```txt
# ...
12:55:34.903 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 774ms. Server Running: http://[0:0:0:0:0:0:0:0]:8080
12:56:07.715 [scheduled-executor-thread-1] INFO  org.pf4j.AbstractPluginManager - Stop plugin 'DummyPluginImpl1@0.0.1'
12:56:07.715 [scheduled-executor-thread-1] INFO  org.pf4j.AbstractPluginManager - Unload plugin 'DummyPluginImpl1@0.0.1'
12:56:07.720 [scheduled-executor-thread-1] INFO  org.pf4j.AbstractPluginManager - Plugin 'DummyPluginImpl1@0.0.2' resolved
12:56:07.720 [scheduled-executor-thread-1] INFO  org.pf4j.AbstractPluginManager - Start plugin 'DummyPluginImpl1@0.0.2'
12:56:07.722 [scheduled-executor-thread-1] INFO  org.pf4j.AbstractPluginManager - Stop plugin 'FooPluginImpl1@0.0.1'
12:56:07.722 [scheduled-executor-thread-1] INFO  org.pf4j.AbstractPluginManager - Unload plugin 'FooPluginImpl1@0.0.1'
12:56:07.726 [scheduled-executor-thread-1] INFO  org.pf4j.AbstractPluginManager - Plugin 'FooPluginImpl1@0.0.2' resolved
12:56:07.726 [scheduled-executor-thread-1] INFO  org.pf4j.AbstractPluginManager - Start plugin 'FooPluginImpl1@0.0.2'
```

New response should be like this:

```sh
$ curl -s "localhost:8080?plugin=plugin1"
{"response":"Response from DummyImpl, provided by plugin: 'PluginDescriptor [pluginId=DummyPluginImpl1, pluginClass=micronaut.pf4j.poc.plugin.impl.DummyPlugin, version=0.0.2, provider=null, dependencies=[], description=This is my first dummy plugin, requires=*, license=null]'","plugin":"DummyPlugin"}

$ curl -s "localhost:8080?plugin=plugin2"
{"response":"Response from FooImpl, provided by plugin: 'PluginDescriptor [pluginId=FooPluginImpl1, pluginClass=micronaut.pf4j.poc.plugin.impl.FooPlugin, version=0.0.2, provider=null, dependencies=[], description=This is my first foo plugin, requires=*, license=null]'","plugin":"FooPlugin"}
```
