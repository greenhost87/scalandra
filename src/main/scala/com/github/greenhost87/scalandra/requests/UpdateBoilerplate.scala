package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.{CassandraFormat, PimpedRow}

trait UpdateBoilerplate extends UpdateCollections {
  this: Table =>

  import PimpedRow._

  def update[C1: CassandraFormat](c1: Table#Column[C1], v1: C1): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1)}",
      v1.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2)}",
      v1.asCV, v2.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3)}",
      v1.asCV, v2.asCV, v3.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18, c19: Table#Column[C19], v19: C19): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV, v19.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat, C20: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18, c19: Table#Column[C19], v19: C19, c20: Table#Column[C20], v20: C20): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV, v19.asCV, v20.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat, C20: CassandraFormat, C21: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18, c19: Table#Column[C19], v19: C19, c20: Table#Column[C20], v20: C20, c21: Table#Column[C21], v21: C21): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV, v19.asCV, v20.asCV, v21.asCV
    )

  def update[C1: CassandraFormat, C2: CassandraFormat, C3: CassandraFormat, C4: CassandraFormat, C5: CassandraFormat, C6: CassandraFormat, C7: CassandraFormat, C8: CassandraFormat, C9: CassandraFormat, C10: CassandraFormat, C11: CassandraFormat, C12: CassandraFormat, C13: CassandraFormat, C14: CassandraFormat, C15: CassandraFormat, C16: CassandraFormat, C17: CassandraFormat, C18: CassandraFormat, C19: CassandraFormat, C20: CassandraFormat, C21: CassandraFormat, C22: CassandraFormat](c1: Table#Column[C1], v1: C1, c2: Table#Column[C2], v2: C2, c3: Table#Column[C3], v3: C3, c4: Table#Column[C4], v4: C4, c5: Table#Column[C5], v5: C5, c6: Table#Column[C6], v6: C6, c7: Table#Column[C7], v7: C7, c8: Table#Column[C8], v8: C8, c9: Table#Column[C9], v9: C9, c10: Table#Column[C10], v10: C10, c11: Table#Column[C11], v11: C11, c12: Table#Column[C12], v12: C12, c13: Table#Column[C13], v13: C13, c14: Table#Column[C14], v14: C14, c15: Table#Column[C15], v15: C15, c16: Table#Column[C16], v16: C16, c17: Table#Column[C17], v17: C17, c18: Table#Column[C18], v18: C18, c19: Table#Column[C19], v19: C19, c20: Table#Column[C20], v20: C20, c21: Table#Column[C21], v21: C21, c22: Table#Column[C22], v22: C22): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET ${asUpdateColumns(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22)}",
      v1.asCV, v2.asCV, v3.asCV, v4.asCV, v5.asCV, v6.asCV, v7.asCV, v8.asCV, v9.asCV, v10.asCV, v11.asCV, v12.asCV, v13.asCV, v14.asCV, v15.asCV, v16.asCV, v17.asCV, v18.asCV, v19.asCV, v20.asCV, v21.asCV, v22.asCV
    )
}