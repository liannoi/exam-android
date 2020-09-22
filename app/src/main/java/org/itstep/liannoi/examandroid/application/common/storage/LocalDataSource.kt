package org.itstep.liannoi.examandroid.application.common.storage

import io.reactivex.Maybe

interface LocalDataSource<TEntity, TKey> {

    fun create(entity: TEntity)

    fun getAll(): Maybe<List<TEntity>>

    fun getById(id: TKey): Maybe<TEntity>

    fun update(entity: TEntity)

    fun delete(entity: TEntity)
}
