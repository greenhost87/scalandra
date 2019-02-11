package com.github.greenhost87.scalandra.ddl

import java.util.UUID

import com.datastax.driver.core.{DataType, TableMetadata}
import org.joda.time.DateTime
import org.scalatest.{Matchers, WordSpec}
import com.github.greenhost87.scalandra.requests.schema.SchemaUpdater
import com.github.greenhost87.scalandra.requests.utils.OrderEnum
import com.github.greenhost87.scalandra.utils.{CassandraScope, EmbeddedCassandra}

import scala.collection.JavaConversions._

class TableSynchronizationSpec
  extends WordSpec
    with Matchers {

  EmbeddedCassandra.start()

  "Table synchronization" should {
    "work as expected with simple key" in new CassandraScope {

      object TestTable extends Table {
        override def tableName: String = "simple_table_synchronization_test"

        val Phone = column
        val IsPhoneVerified = column[Boolean]()
        val CreatedAt = column[DateTime]()
        val Balance = column[BigDecimal]()
        val Id = column[UUID]()

        override val partitionKeys = Seq(Phone)
      }

      SchemaUpdater.synchronize(Seq(TestTable), keyspace, cql3Cluster)

      val tableOption = cql3Cluster.getMetadata.getKeyspace(keyspace).getTables.find(_.getName == TestTable.tableName)

      tableOption.isEmpty shouldBe false

      val table = tableOption.get

      table.getPartitionKey should have size 1

      table.getColumn(TestTable.Phone).getType shouldBe DataType.text()
      table.getColumn(TestTable.IsPhoneVerified).getType shouldBe DataType.cboolean()
      table.getColumn(TestTable.CreatedAt).getType shouldBe DataType.timestamp()
      table.getColumn(TestTable.Balance).getType shouldBe DataType.text()
      table.getColumn(TestTable.Id).getType shouldBe DataType.uuid()
    }

    "work as expected with static columns" in new CassandraScope {

      object TestTable extends Table {
        override def tableName: String = "static_table_synchronization_test"

        val Phone = column
        val Id = column[UUID]()
        val Balance = staticColumn[BigDecimal]

        override val partitionKeys = Seq(Phone)
        override val clusteringKeys = Seq(Id)
      }

      SchemaUpdater.synchronize(Seq(TestTable), keyspace, cql3Cluster)

      val tableOption = cql3Cluster.getMetadata.getKeyspace(keyspace).getTables.find(_.getName == TestTable.tableName)

      tableOption.isEmpty shouldBe false

      val table = tableOption.get

      table.getPartitionKey should have size 1

      table.getColumn(TestTable.Phone).getType shouldBe DataType.text()
      table.getColumn(TestTable.Balance).getType shouldBe DataType.text()
      table.getColumn(TestTable.Id).getType shouldBe DataType.uuid()
      table.getColumn(TestTable.Balance).isStatic shouldBe true
    }

    "work as expected composite partition key" in new CassandraScope {

      object TestTable extends Table {
        override def tableName: String = "composite_partition_key"

        val Phone = column
        val IsPhoneVerified = column[Boolean]()
        val CreatedAt = column[DateTime]()
        val Balance = column[BigDecimal]()
        val Id = column[UUID]()

        override val partitionKeys = Seq(Phone, CreatedAt)
      }

      SchemaUpdater.synchronize(Seq(TestTable), keyspace, cql3Cluster)

      val tableOption: Option[TableMetadata] = cql3Cluster.getMetadata.getKeyspace(keyspace).getTables.find(_.getName == TestTable.tableName)

      tableOption should not be empty

      val table = tableOption.get

      table.getPrimaryKey should have size 2
    }

    "work as expected composite partition key and simple clustering key" in new CassandraScope {

      object TestTable extends Table {
        override def tableName: String = "partition_and_clustering_keys"

        val Phone = column
        val IsPhoneVerified = column[Boolean]()
        val CreatedAt = column[DateTime]()
        val Balance = column[BigDecimal]()
        val Id = column[UUID]()

        override val partitionKeys = Seq(Phone, Id)
        override val clusteringKeys = Seq(CreatedAt)
      }

      SchemaUpdater.synchronize(Seq(TestTable), keyspace, cql3Cluster)

      val tableOption = cql3Cluster.getMetadata.getKeyspace(keyspace).getTables.find(_.getName == TestTable.tableName)

      tableOption should not be empty

      val table = tableOption.get

      table.getPartitionKey should have size 2
      table.getClusteringColumns should have size 1
    }

    "work as expected composite partition key and simple clustering key with ordering" in new CassandraScope {

      object TestTable extends Table {
        override def tableName: String = "partition_and_ordered_clustering_keys"

        val Phone = column
        val IsPhoneVerified = column[Boolean]()
        val CreatedAt = column[DateTime]()
        val Balance = column[BigDecimal]()
        val Id = column[UUID]()

        override val partitionKeys = Seq(Phone, Id)
        override val clusteringKeys = Seq(CreatedAt)
        override val clusteringOrderBy = Seq((CreatedAt, OrderEnum.ASC))
      }

      SchemaUpdater.synchronize(Seq(TestTable), keyspace, cql3Cluster)

      val tableOption = cql3Cluster.getMetadata.getKeyspace(keyspace).getTables.find(_.getName == TestTable.tableName)

      tableOption should not be empty

      val table = tableOption.get

      table.getPartitionKey should have size 2
      table.getClusteringColumns should have size 1
      table.getClusteringOrder should have size 1
    }
  }
}
