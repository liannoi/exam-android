package org.itstep.liannoi.examandroid.application.core.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.itstep.liannoi.examandroid.application.common.storage.LocalDataSource
import org.itstep.liannoi.examandroid.application.storage.users.models.User
import org.itstep.liannoi.examandroid.application.storage.users.sources.UsersLocalDataSource
import org.itstep.liannoi.examandroid.infrastructure.persistence.CrudDatabase
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class UsersLocalSource

    @Singleton
    @UsersLocalSource
    @Provides
    fun provideUsersLocalDataSource(database: CrudDatabase): LocalDataSource<User, Int> =
        UsersLocalDataSource(database.userDao())

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CrudDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            CrudDatabase::class.java,
            "users.db"
        ).build()
}
