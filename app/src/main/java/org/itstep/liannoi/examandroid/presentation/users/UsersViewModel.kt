package org.itstep.liannoi.examandroid.presentation.users

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.itstep.liannoi.examandroid.application.common.interfaces.UsersRepository
import org.itstep.liannoi.examandroid.application.storage.core.seeding.SeedingCommand
import org.itstep.liannoi.examandroid.application.storage.users.commands.CreateCommand
import org.itstep.liannoi.examandroid.application.storage.users.commands.DeleteCommand
import org.itstep.liannoi.examandroid.application.storage.users.commands.UpdateCommand
import org.itstep.liannoi.examandroid.application.storage.users.models.User
import org.itstep.liannoi.examandroid.application.storage.users.queries.ListQuery
import java.util.*

class UsersViewModel @ViewModelInject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    init {
        SeedingCommand.Handler(usersRepository, disposable).handle(SeedingHandler())
    }

    fun create() {
        usersRepository.create(
            CreateCommand(User("Petro", UUID.randomUUID().toString().take(20))),
            CreateCommandHandler()
        )
    }

    fun update() {
        val user: User = _users.value!!.last()
        user.lastName = "Updated!"
        usersRepository.update(UpdateCommand(user), UpdateCommandHandler())
    }

    fun delete() {
        usersRepository.delete(DeleteCommand(_users.value!!.last()), DeleteCommandHandler())
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helpers
    ///////////////////////////////////////////////////////////////////////////

    private fun refresh() {
        usersRepository.getAll(ListQuery(), ListQueryHandler())
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal members
    ///////////////////////////////////////////////////////////////////////////

    private inner class SeedingHandler : SeedingCommand.Notification {

        override fun onSuccess() {
            refresh()
        }
    }

    private inner class ListQueryHandler : ListQuery.NotificationHandler() {

        override fun onSuccess(users: List<User>) {
            this@UsersViewModel._users.postValue(users)
        }
    }

    private inner class CreateCommandHandler : CreateCommand.NotificationHandler() {

        override fun onSuccess() {
            refresh()
        }
    }

    private inner class UpdateCommandHandler : UpdateCommand.NotificationHandler() {

        override fun onSuccess() {
            refresh()
        }
    }

    private inner class DeleteCommandHandler : DeleteCommand.NotificationHandler() {

        override fun onSuccess() {
            refresh()
        }
    }
}
