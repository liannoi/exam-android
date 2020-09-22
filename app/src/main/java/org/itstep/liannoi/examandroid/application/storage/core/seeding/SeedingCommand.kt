package org.itstep.liannoi.examandroid.application.storage.core.seeding

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import org.itstep.liannoi.examandroid.application.common.interfaces.UsersRepository

class SeedingCommand {

    class Handler constructor(
        private val usersRepository: UsersRepository,
        private val disposable: CompositeDisposable
    ) {

        fun handle(callback: Notification) {
            Completable.fromAction { SampleDataSeeder(usersRepository).seedAll() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { callback.onSuccess() }
                .addTo(disposable)
        }
    }

    interface Notification {

        fun onSuccess()
    }
}
