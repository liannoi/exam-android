package org.itstep.liannoi.examandroid.application.storage.users.commands

class DeleteCommand constructor(
    val userId: Int
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