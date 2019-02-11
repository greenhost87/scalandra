package com.github.greenhost87.scalandra.requests.schema

object EnumReplicationStrategy extends Enumeration {
  type ReplicationStrategy = Value

  val SimpleStrategy, NetworkTopologyStrategy = Value
}