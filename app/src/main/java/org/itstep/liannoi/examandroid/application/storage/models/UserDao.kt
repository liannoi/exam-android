package org.itstep.liannoi.examandroid.application.storage.models

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Maybe
import org.itstep.liannoi.examandroid.application.ApplicationDefaults
import org.itstep.liannoi.examandroid.application.common.storage.BaseDao

@Dao
interface UserDao : BaseDao<User> {

    @Query("SELECT U.UserId, U.FirstName, U.LastName FROM Users AS U ORDER BY U.UserId ASC LIMIT :itemsPerPage OFFSET :startPosition")
    fun getAll(
        startPosition: Int = ApplicationDefaults.PAGING_START_POSITION,
        itemsPerPage: Int = ApplicationDefaults.PAGING_ITEMS_PER_PAGE
    ): Maybe<List<User>>
}
