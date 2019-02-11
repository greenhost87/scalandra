package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.{CassandraFormat, PimpedRow}
import com.github.greenhost87.scalandra.requests.executors.BaseExecutor

trait SelectBoilerplate extends BaseExecutor {
  this: Table with Select =>

  import PimpedRow._
  import com.github.greenhost87.scalandra.requests.utils.Const._

  def select[T: CassandraFormat]: CassandraSimpleSelectRequest[T] = {
    val columns = columnsForType[T].mkString(delimiter)
    val query = s"SELECT $columns FROM $tableName"

    CassandraSimpleSelectRequest[T](query, _.asCV[T](this).convertTo[T])
  }


  def select[C1: CassandraFormat](c1: Table#Column[_]): CassandraSimpleSelectRequest[C1] = {
    val query = s"SELECT ${columnsToRequest(c1)} FROM $tableName"

    CassandraSimpleSelectRequest[C1](query, r => (r asCV c1).convertTo[C1])
  }

  def select[C1: CassandraFormat, C2: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_], c14: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13],
        r.asCV(c14).convertTo[C14]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_], c14: Table#Column[_], c15: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13],
        r.asCV(c14).convertTo[C14],
        r.asCV(c15).convertTo[C15]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_], c14: Table#Column[_], c15: Table#Column[_], c16: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13],
        r.asCV(c14).convertTo[C14],
        r.asCV(c15).convertTo[C15],
        r.asCV(c16).convertTo[C16]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_], c14: Table#Column[_], c15: Table#Column[_], c16: Table#Column[_], c17: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13],
        r.asCV(c14).convertTo[C14],
        r.asCV(c15).convertTo[C15],
        r.asCV(c16).convertTo[C16],
        r.asCV(c17).convertTo[C17]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_], c14: Table#Column[_], c15: Table#Column[_], c16: Table#Column[_], c17: Table#Column[_], c18: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13],
        r.asCV(c14).convertTo[C14],
        r.asCV(c15).convertTo[C15],
        r.asCV(c16).convertTo[C16],
        r.asCV(c17).convertTo[C17],
        r.asCV(c18).convertTo[C18]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_], c14: Table#Column[_], c15: Table#Column[_], c16: Table#Column[_], c17: Table#Column[_], c18: Table#Column[_], c19: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13],
        r.asCV(c14).convertTo[C14],
        r.asCV(c15).convertTo[C15],
        r.asCV(c16).convertTo[C16],
        r.asCV(c17).convertTo[C17],
        r.asCV(c18).convertTo[C18],
        r.asCV(c19).convertTo[C19]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat, C20: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_], c14: Table#Column[_], c15: Table#Column[_], c16: Table#Column[_], c17: Table#Column[_], c18: Table#Column[_], c19: Table#Column[_], c20: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19, C20)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19, C20)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13],
        r.asCV(c14).convertTo[C14],
        r.asCV(c15).convertTo[C15],
        r.asCV(c16).convertTo[C16],
        r.asCV(c17).convertTo[C17],
        r.asCV(c18).convertTo[C18],
        r.asCV(c19).convertTo[C19],
        r.asCV(c20).convertTo[C20]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat, C20: CassandraFormat, C21: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_], c14: Table#Column[_], c15: Table#Column[_], c16: Table#Column[_], c17: Table#Column[_], c18: Table#Column[_], c19: Table#Column[_], c20: Table#Column[_], c21: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19, C20, C21)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19, C20, C21)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13],
        r.asCV(c14).convertTo[C14],
        r.asCV(c15).convertTo[C15],
        r.asCV(c16).convertTo[C16],
        r.asCV(c17).convertTo[C17],
        r.asCV(c18).convertTo[C18],
        r.asCV(c19).convertTo[C19],
        r.asCV(c20).convertTo[C20],
        r.asCV(c21).convertTo[C21]
      )
    })
  }

  def select[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat, C20: CassandraFormat, C21: CassandraFormat, C22: CassandraFormat](c1: Table#Column[_], c2: Table#Column[_], c3: Table#Column[_], c4: Table#Column[_], c5: Table#Column[_], c6: Table#Column[_], c7: Table#Column[_], c8: Table#Column[_], c9: Table#Column[_], c10: Table#Column[_], c11: Table#Column[_], c12: Table#Column[_], c13: Table#Column[_], c14: Table#Column[_], c15: Table#Column[_], c16: Table#Column[_], c17: Table#Column[_], c18: Table#Column[_], c19: Table#Column[_], c20: Table#Column[_], c21: Table#Column[_], c22: Table#Column[_]): CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19, C20, C21, C22)] = {
    val query = s"SELECT ${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22)} FROM $tableName"

    CassandraSimpleSelectRequest[(C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19, C20, C21, C22)](query, { r =>
      (
        r.asCV(c1).convertTo[C1],
        r.asCV(c2).convertTo[C2],
        r.asCV(c3).convertTo[C3],
        r.asCV(c4).convertTo[C4],
        r.asCV(c5).convertTo[C5],
        r.asCV(c6).convertTo[C6],
        r.asCV(c7).convertTo[C7],
        r.asCV(c8).convertTo[C8],
        r.asCV(c9).convertTo[C9],
        r.asCV(c10).convertTo[C10],
        r.asCV(c11).convertTo[C11],
        r.asCV(c12).convertTo[C12],
        r.asCV(c13).convertTo[C13],
        r.asCV(c14).convertTo[C14],
        r.asCV(c15).convertTo[C15],
        r.asCV(c16).convertTo[C16],
        r.asCV(c17).convertTo[C17],
        r.asCV(c18).convertTo[C18],
        r.asCV(c19).convertTo[C19],
        r.asCV(c20).convertTo[C20],
        r.asCV(c21).convertTo[C21],
        r.asCV(c22).convertTo[C22]
      )
    })
  }

}