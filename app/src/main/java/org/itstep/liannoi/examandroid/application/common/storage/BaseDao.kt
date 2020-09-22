package org.itstep.liannoi.examandroid.application.common.storage

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<TEntity> {

    @Insert
    fun create(entity: TEntity)

    @Update
    fun update(user: TEntity)

    @Delete
    fun delete(user: TEntity)
}
