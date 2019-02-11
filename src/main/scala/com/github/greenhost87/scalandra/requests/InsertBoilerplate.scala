package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.CassandraFormat
import com.github.greenhost87.scalandra.requests.executors.BaseExecutor

trait InsertBoilerplate extends BaseExecutor {
  this: Table with Insert =>

  import com.github.greenhost87.scalandra.formats.PimpedRow._

  def insert[C1: CassandraFormat, C2: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2)}) VALUES (?, ?)",
      v1.asCV, v2.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3)}) VALUES (?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4)}) VALUES (?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5)}) VALUES (?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6)}) VALUES (?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7)}) VALUES (?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18, c19: Table#Column[C19], v19: C19): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV, v19.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat, C20: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18, c19: Table#Column[C19], v19: C19, c20: Table#Column[C20], v20: C20): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV, v19.asCV, v20.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat, C20: CassandraFormat, C21: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18, c19: Table#Column[C19], v19: C19, c20: Table#Column[C20], v20: C20, c21: Table#Column[C21], v21: C21): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV, v19.asCV, v20.asCV, v21.asCV
    )

  def insert[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat, C20: CassandraFormat, C21: CassandraFormat, C22: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18, c19: Table#Column[C19], v19: C19, c20: Table#Column[C20], v20: C20, c21: Table#Column[C21], v21: C21, c22: Table#Column[C22], v22: C22): CassandraInsertRequest =
    CassandraInsertRequest(
      s"INSERT INTO $tableName (${columnsToRequest(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22)}) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV, v19.asCV, v20.asCV, v21.asCV, v22.asCV
    )
}
