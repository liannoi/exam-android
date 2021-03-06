package org.itstep.liannoi.examandroid.application.storage.users.commands

import org.itstep.liannoi.examandroid.application.storage.users.models.User

class UpdateCommand constructor(
    val user: User
) {

    interface Notification {

        fun onSuccess()

        fun onError(throwable: Throwable)
    }

    abstract class NotificationHandler : Notification {

        override fun onSuccess() {
            /* no-op */
        }

        override fun onError(throwable: Throwable) {
            /* no-op */
        }
    }
}
