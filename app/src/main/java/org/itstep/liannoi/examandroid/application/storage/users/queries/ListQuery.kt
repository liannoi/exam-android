package org.itstep.liannoi.examandroid.application.storage.users.queries

import org.itstep.liannoi.examandroid.application.storage.users.models.User

class ListQuery {

    interface Notification {

        fun onSuccess(users: List<User>)

        fun onError(throwable: Throwable)
    }

    abstract class NotificationHandler : Notification {

        override fun onSuccess(users: List<User>) {
            /* no-op */
        }

        override fun onError(throwable: Throwable) {
            /* no-op */
        }
    }
}
