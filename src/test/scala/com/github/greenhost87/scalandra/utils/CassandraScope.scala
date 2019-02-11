package com.github.greenhost87.scalandra.utils

import com.datastax.driver.core.Cluster

trait CassandraScope {
  val keyspace: String = EmbeddedCassandra.keyspaceName

  lazy val cql3Cluster: Cluster = Cluster
    .builder()
    .addContactPoints("localhost")
    .withPort(9142)
    .withoutJMXReporting()
    .withoutMetrics()
    .build()
}