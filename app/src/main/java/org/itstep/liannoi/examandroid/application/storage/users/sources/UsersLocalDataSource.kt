package org.itstep.liannoi.examandroid.application.storage.users.sources

import io.reactivex.Maybe
import org.itstep.liannoi.examandroid.application.common.storage.LocalDataSource
import org.itstep.liannoi.examandroid.application.storage.users.models.User
import org.itstep.liannoi.examandroid.application.storage.users.models.UserDao

class UsersLocalDataSource constructor(
    private val userDao: UserDao
) : LocalDataSource<User, Int> {

    override fun create(entity: User) {
        userDao.create(entity)
    }

    override fun getAll(): Maybe<List<User>> = userDao.getAll()

    override fun getById(id: Int): Maybe<User> = userDao.getById(id)

    override fun update(entity: User) {
        userDao.update(entity)
    }

    override fun delete(entity: User) {
        userDao.delete(entity)
    }
}
