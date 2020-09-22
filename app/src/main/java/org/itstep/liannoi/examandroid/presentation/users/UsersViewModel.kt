package org.itstep.liannoi.examandroid.presentation.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.itstep.liannoi.examandroid.application.common.interfaces.UsersRepository
import org.itstep.liannoi.examandroid.application.storage.core.seeding.SeedingCommand
import org.itstep.liannoi.examandroid.application.storage.users.models.User
import org.itstep.liannoi.examandroid.application.storage.users.queries.ListQuery

class UsersViewModel @ViewModelInject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    init {
        SeedingCommand.Handler(usersRepository, disposable).handle(SeedingHandler())
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal members
    ///////////////////////////////////////////////////////////////////////////

    private inner class SeedingHandler : SeedingCommand.Notification {

        override fun onSuccess() {
            usersRepository.getAll(ListQuery(), ListQueryHandler())
        }
    }

    private inner class ListQueryHandler : ListQuery.NotificationHandler() {

        override fun onSuccess(users: List<User>) {
            this@UsersViewModel._users.value = users
        }
    }
}
