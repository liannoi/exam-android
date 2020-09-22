package org.itstep.liannoi.examandroid.application.storage.users.queries

import org.itstep.liannoi.examandroid.application.storage.users.models.User

class DetailQuery constructor(
    val userId: Int
) {

    interface Notification {

        fun onSuccess(user: User)

        fun onError(throwable: Throwable)
    }

    abstract class NotificationHandler : Notification {

        override fun onSuccess(user: User) {
            /* no-op */
        }

        override fun onError(throwable: Throwable) {
            /* no-op */
        }
    }
}
