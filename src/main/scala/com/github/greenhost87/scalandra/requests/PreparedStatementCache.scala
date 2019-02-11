package com.github.greenhost87.scalandra.requests

import java.util.concurrent.{Callable, TimeUnit}

import com.datastax.driver.core.{ConsistencyLevel, PreparedStatement, Session}
import com.google.common.cache.{Cache, CacheBuilder}

object PreparedStatementCache {
  private val cache: Cache[(ConsistencyLevel, String, Int), PreparedStatement] =
    CacheBuilder
      .newBuilder()
      .maximumSize(10000)
      .expireAfterWrite(12, TimeUnit.HOURS)
      .build()

  def prepare(request: String, consistencyLevel: ConsistencyLevel)(implicit session: Session): PreparedStatement = {
    cache.get((consistencyLevel, request, session.hashCode()), new Callable[PreparedStatement]() {
      override def call(): PreparedStatement = {
        session
          .prepare(request)
          .setConsistencyLevel(consistencyLevel)
      }
    })
  }
}