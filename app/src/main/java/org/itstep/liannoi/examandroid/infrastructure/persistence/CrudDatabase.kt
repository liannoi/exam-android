package org.itstep.liannoi.examandroid.infrastructure.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import org.itstep.liannoi.examandroid.application.storage.users.models.User
import org.itstep.liannoi.examandroid.application.storage.users.models.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class CrudDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
