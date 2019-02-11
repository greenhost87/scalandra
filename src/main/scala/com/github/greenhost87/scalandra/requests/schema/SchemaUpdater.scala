package com.github.greenhost87.scalandra.requests.schema

import com.datastax.driver.core.{Cluster, Session, TableMetadata}
import com.github.greenhost87.scalandra.ddl.Table
import org.slf4j.{Logger, LoggerFactory}

object SchemaUpdater {

  import scala.collection.JavaConversions._

  val log: Logger = LoggerFactory.getLogger(getClass)

  def synchronize(tables: Seq[Table], keyspaceName: String, cluster: Cluster): Boolean =
    synchronize(tables, keyspaceName, cluster, 1)

  def synchronize(tables: Seq[Table], keyspaceName: String, cluster: Cluster, replicationFactor: Int): Boolean = {
    log.info(s"Start synchronize keyspace $keyspaceName")

    if (cluster.getMetadata.getKeyspaces.forall(_.getName != keyspaceName)) {
      log.info(s"Keyspace $keyspaceName not found, create it")
      val session = cluster.newSession()
      createKeyspace(keyspaceName, session, replicationFactor)
      session.close()
    }

    if (tables.nonEmpty) {
      val session = cluster.connect(keyspaceName)
      val keyspaceMetadata = cluster.getMetadata.getKeyspace(keyspaceName)

      tables.foreach {
        table =>
          log.info(s"Synchronize table ${table.tableName} in $keyspaceName")
          synchronizeTable(table, Option(keyspaceMetadata.getTable(table.tableName)), session)
      }

      session.close()
    }
    true
  }

  private def synchronizeTable(ddl: Table, existingTable: Option[TableMetadata], session: Session) =
    existingTable match {
      case Some(table) =>
        updateTable(ddl, table, session)
      case None =>
        createTable(ddl, session)
    }

  private def updateTable(ddl: Table, existingTable: TableMetadata, session: Session): Unit = {
    ddl.columns.foreach(column => {
      existingTable.getColumns.find(_.getName.toLowerCase == column.name.toLowerCase) match {
        case Some(_) =>
        // nothing to do
        case None =>
          createColumn(existingTable.getName, column, session)
      }
    })
  }

  private def createKeyspace(name: String, session: Session, replicationFactor: Int): Unit =
    session.execute( s"""CREATE KEYSPACE "$name" WITH replication = {'class': 'SimpleStrategy', 'replication_factor': $replicationFactor};""")

  // TODO: chenge or remove it
  private def dropIndex(indexName: String, session: Session): Unit =
    session.execute(s"DROP INDEX $indexName;")

  private def createColumn(tableName: String, column: Table#Column[_], session: Session): Unit = {
    session.execute( s"""ALTER TABLE "$tableName" ADD ${makeColumn(column)}""")
  }

  def createTable(table: Table, session: Session): Unit = {
    val header = s"CREATE TABLE ${table.tableName}"

    val columns = table.columns.map(makeColumn).mkString(",")

    val partitionKey = table.partitionKeys.mkString(",")

    val primaryKey = if (table.clusteringKeys.isEmpty) {
      s"PRIMARY KEY ($partitionKey)"
    } else {
      val clusteringKey = table.clusteringKeys.mkString(",")
      s"PRIMARY KEY (($partitionKey), $clusteringKey)"
    }

    val query = s"$header ($columns, $primaryKey) ${withClusteringOrder(table)};"

    log.debug(s"executing query: $query")
    session.execute(query)
  }

  private def withClusteringOrder(table: Table): String =
    if (table.clusteringOrderBy.isEmpty) {
      ""
    } else {
      if (isCompactStorage(table)) {
        throw new IllegalArgumentException(s"Table ${table.tableName} have bad description because 'CLUSTERING ORDER' can't be used together with 'COMPACT STORAGE'")
      }

      if (!table.clusteringKeys.containsSlice(table.clusteringOrderBy.map(_._1))) {
        throw new IllegalArgumentException(s"Table ${table.tableName} have bad description because only clustering keys can be used in 'CLUSTERING ORDER'")
      }

      "WITH CLUSTERING ORDER BY (" + table.clusteringOrderBy.map(o => s"${o._1} ${o._2}").mkString(",") + ")"
    }

  private def isCompactStorage(table: Table): Boolean =
    table.partitionKeys.size == 1 && table.clusteringKeys.isEmpty

  private def makeColumn(column: Table#Column[_]): String = {
    val staticFlag: String = if (column.isStatic) "STATIC" else ""

    s"$column ${column.cassandraTypeName} $staticFlag"
  }
}
