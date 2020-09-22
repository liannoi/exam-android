package org.itstep.liannoi.examandroid.application.storage.users.models

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Maybe
import org.itstep.liannoi.examandroid.application.common.storage.BaseDao

@Dao
interface UserDao : BaseDao<User> {

    @Query("SELECT * FROM Users")
    fun getAll(): Maybe<List<User>>

    @Query("SELECT * FROM Users WHERE userId = :id")
    fun getById(id: Int): Maybe<User>
}
