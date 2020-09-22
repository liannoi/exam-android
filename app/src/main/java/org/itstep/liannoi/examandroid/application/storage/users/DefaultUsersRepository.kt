package org.itstep.liannoi.examandroid.application.storage.users

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.itstep.liannoi.examandroid.application.common.interfaces.UsersRepository
import org.itstep.liannoi.examandroid.application.common.storage.LocalDataSource
import org.itstep.liannoi.examandroid.application.storage.users.commands.CreateCommand
import org.itstep.liannoi.examandroid.application.storage.users.commands.DeleteCommand
import org.itstep.liannoi.examandroid.application.storage.users.commands.UpdateCommand
import org.itstep.liannoi.examandroid.application.storage.users.models.User
import org.itstep.liannoi.examandroid.application.storage.users.queries.DetailQuery
import org.itstep.liannoi.examandroid.application.storage.users.queries.ListQuery
import org.itstep.liannoi.examandroid.application.storage.users.sources.UsersRemoteDataSource

class DefaultUsersRepository constructor(
    private val localDataSource: LocalDataSource<User, Int>,
    private val remoteDataSource: UsersRemoteDataSource? = null
) : UsersRepository {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun create(request: CreateCommand, handler: CreateCommand.Notification) {
        Completable.fromAction { localDataSource.create(request.user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handler.onSuccess() },
                { handler.onError(it) }
            ).addTo(disposable)
    }

    override fun getAll(request: ListQuery, handler: ListQuery.Notification) {
        localDataSource.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handler.onSuccess(it) },
                { handler.onError(it) }
            ).addTo(disposable)
    }

    override fun getById(request: DetailQuery, handler: DetailQuery.Notification) {
        localDataSource.getById(request.userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handler.onSuccess(it) },
                { handler.onError(it) }
            ).addTo(disposable)
    }

    override fun update(request: UpdateCommand, handler: UpdateCommand.Notification) {
        Completable.fromAction { localDataSource.update(request.user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handler.onSuccess() },
                { handler.onError(it) }
            ).addTo(disposable)
    }

    override fun delete(request: DeleteCommand, handler: DeleteCommand.Notification) {
        Completable.fromAction { localDataSource.delete(request.userId) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handler.onSuccess() },
                { handler.onError(it) }
            ).addTo(disposable)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Dispose
    ///////////////////////////////////////////////////////////////////////////

    override fun stop() {
        disposable.clear()
    }

    override fun destroy() {
        disposable.dispose()
    }
}

/**
 * https://github.com/ReactiveX/RxKotlin/blob/3.x/src/main/kotlin/io/reactivex/rxjava3/kotlin/disposable.kt
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable =
    apply { compositeDisposable.add(this) }
