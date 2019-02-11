package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.DefaultFormats
import com.github.greenhost87.scalandra.requests.schema.SchemaUpdater
import com.github.greenhost87.scalandra.requests.utils.{EqualityEnum, Succeed}
import com.github.greenhost87.scalandra.utils.{CassandraScope, EmbeddedCassandra}
import org.scalatest.{AsyncWordSpec, Matchers, OneInstancePerTest}

class UpdateCollectionSpec extends AsyncWordSpec
  with Matchers
  with CassandraScope
  with OneInstancePerTest
  with TestTable
  with DefaultFormats {

  EmbeddedCassandra.start()

  SchemaUpdater.synchronize(Seq(CollectionsTable), keyspace, cql3Cluster)

  implicit val session = cql3Cluster.connect(keyspace)

  object CollectionsTable extends Table {
    override def tableName: String = "collections_table"

    val ID = column
    val ClusteringId = column
    val MapColumn = staticColumn[Map[String, String]]()

    override def partitionKeys = Seq(ID)

    override def clusteringKeys = Seq(ClusteringId)
  }

  import CollectionsTable._

  "update collections" should {
    "correct update Map collection via add new element" in {
      val id = "test_map"

      CollectionsTable
        .updateCollection(MapColumn, ("key", "value"))
        .where(ID, id)
        .execute() shouldBe Succeed

      CollectionsTable
        .select[Map[String, String]](MapColumn)
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe Map("key" -> "value")
    }
    "correct update Map collection via override existing element" in {
      val id = "test_map_override"

      CollectionsTable
        .updateCollection(MapColumn, ("key", "value"))
        .where(ID, id)
        .execute() shouldBe Succeed
      CollectionsTable
        .updateCollection(MapColumn, ("key", "new_value"))
        .where(ID, id)
        .execute() shouldBe Succeed

      CollectionsTable
        .select[Map[String, String]](MapColumn)
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe Map("key" -> "new_value")
    }
  }
}