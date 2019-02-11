package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.formats.CassandraValue
import com.github.greenhost87.scalandra.requests.executors.WriteExecutor
import org.slf4j.{Logger, LoggerFactory}

case class CassandraBatch(requests: CassandraBatchItem*) extends WriteExecutor {
  // Method should not be called
  override def values: Seq[CassandraValue] = {
    throw new IllegalStateException("Method should not be called")
  }

  override val log: Logger = LoggerFactory.getLogger(getClass)

  override lazy val query: String = {
    s"""BEGIN BATCH
       ${requests.map(_.query).mkString(";\n")};
       APPLY BATCH;
       """
  }

  override lazy val convertedValues: Seq[Object] =
    requests.flatMap(_.parameters)
}

case class CassandraBatchItem(query: String, parameters: Seq[Object])
