package org.itstep.liannoi.examandroid.application.storage.core.seeding

import org.itstep.liannoi.examandroid.application.common.interfaces.UsersRepository
import org.itstep.liannoi.examandroid.application.common.storage.Seeder
import org.itstep.liannoi.examandroid.application.storage.users.commands.CreateCommand
import org.itstep.liannoi.examandroid.application.storage.users.models.User
import org.itstep.liannoi.examandroid.application.storage.users.queries.ListQuery
import java.util.*
import kotlin.random.Random.Default.nextInt

class SampleDataSeeder constructor(
    private val usersRepository: UsersRepository
) : Seeder {

    override fun seedAll() {
        usersRepository.getAll(ListQuery(), ListQueryHandler())
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal members
    ///////////////////////////////////////////////////////////////////////////

    private inner class ListQueryHandler : ListQuery.NotificationHandler() {

        override fun onSuccess(users: List<User>) {
            if (users.isNotEmpty()) return

            seedUsers()
        }

        ///////////////////////////////////////////////////////////////////////////
        // Helpers
        ///////////////////////////////////////////////////////////////////////////

        private fun seedUsers() {
            val users: MutableList<User> = mutableListOf(
                User("Sherri", "Mason", 6100),
                User("Gary", "Carroll", 2900),
                User("Margaret", "Horne", 3100),
                User("Michael", "Rodriquez", 4600),
                User("Patricia", "Bookout", 4000)
            )

            (0..100).forEach { _ ->
                users.add(
                    User(
                        UUID.randomUUID().toString().take(20),
                        UUID.randomUUID().toString().take(20),
                        nextInt(300, 10000)
                    )
                )
            }

            val handler = CreateCommandHandler()
            users.stream().forEach { usersRepository.create(CreateCommand(it), handler) }
        }
    }

    private class CreateCommandHandler : CreateCommand.NotificationHandler()
}
