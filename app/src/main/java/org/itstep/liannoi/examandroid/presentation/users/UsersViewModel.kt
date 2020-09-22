package org.itstep.liannoi.examandroid.presentation.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.itstep.liannoi.examandroid.application.storage.models.User

class UsersViewModel : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users
}
