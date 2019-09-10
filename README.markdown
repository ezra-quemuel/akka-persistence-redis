# Akka Persistence Redis Plugin
[![Build Status](https://travis-ci.org/ezra-quemuel/akka-persistence-redis.svg?branch=master)](https://travis-ci.org/safety-data/akka-persistence-redis)

- [Scaladoc](https://safety-data.github.io/akka-persistence-redis/latest/api/index.html)
<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [What is it?](#what-is-it)
- [Installation](#installation)
- [(De)Serialization](#deserialization)
- [Using the Journal Component](#using-the-journal-component)
  - [Tags](#tags)
- [Using the Snapshot Component](#using-the-snapshot-component)
- [Using the Journal Query Interface Component](#using-the-journal-query-interface-component)
- [Database Configuration](#database-configuration)
- [License](#license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## What is it?

Akka Persistence Redis Plugin is a plugin for [Akka persistence](http://doc.akka.io/docs/akka/2.4/scala/persistence.html) that provides several components:
 - a journal store ;
 - a snapshot store ;
 - a journal query interface implementation.

This plugin stores data in a [redis](https://redis.io) database.

## Installation

The plugin is compiled with Scala 2.12 and 2.11 and are deployed on maven sonatype repositories.
To use the plugin add this to your sbt build file :

```scala
libraryDependencies += "com.ezra-quemuel" %% "akka-persistence-redis" % "0.4.1"
```

_Note_: for snapshot versions you will need to add the sonatype snapshot resolver.

## (De)Serialization

The journal and snapshot components save serialized values into the database.
They rely on the [Akka serialization extension](http://doc.akka.io/docs/akka/2.4/scala/serialization.html).
Custom serialization can be added to handle you data model elements as you wish.

## Using the Journal Component

To use the journal component, you need to enable it in your configuration. To use the default settings, simply add this line:

```scala
akka.persistence.journal.plugin = "akka-persistence-redis.journal"
```

### Tags

The journal component has support for tagged events.
All events wrapped inside an instance of class `akka.persistence.journal.Tagged` are associated to the provided tags in the data store.

## Using the Snapshot Component

To use the snapshot component, you need to enable it in your configuration. To use the default settings, simply add this line:

```scala
akka.persistence.snapshot.plugin = "akka-persistence-redis.snapshot"
```

## Using the Journal Query Interface Component

To use the journal query component, you need to enable it in your configuration. To use the default settings, simply add this line:

```scala
import akka.persistence.query._
import akka.persistence.query.journal.redis._

val readJournal = PersistenceQuery(system)
  .readJournalFor[ScalaReadJournal]("akka-persistence-redis.read-journal")
```

For more details on the available capabilities of the journal query, please refer to the [API documentation](https://safety-data.github.io/akka-persistence-redis/latest/api/akka/persistence/query/journal/redis/ScalaReadJournal.html).

## Database Configuration

This plugin uses [rediscala](https://github.com/Ma27/rediscala) to connect to redis databases and is configured using [Typesafe config](https://github.com/typesafehub/config/) library.

Akka persistence redis plugin supports following modes for connecting to Redis:
 - simple
 - sentinel

A default database configuration is used for all components, under the path `akka-persistence-redis.redis`. You may either override this path to apply changes to all components, or override it locally for each component.

See the [reference configuration](src/main/resources/reference.conf) for more details on possible configuration.

## License

This work is inspired by [Akka Persistence Redis Plugin](https://github.com/hootsuite/akka-persistence-redis) by HootSuite Media Inc licensed under Apache license version 2.

Copyright © 2017 Safety Data - CFH SAS.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Notes

This is a fork of https://github.com/safety-data/akka-persistence-redis. Once https://github.com/safety-data/akka-persistence-redis/pulls is merged in, this fork will be obsolete.