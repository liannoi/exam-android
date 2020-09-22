package org.itstep.liannoi.examandroid.application.common.interfaces

import org.itstep.liannoi.examandroid.application.common.storage.Disposable
import org.itstep.liannoi.examandroid.application.common.storage.Repository
import org.itstep.liannoi.examandroid.application.storage.users.commands.CreateCommand
import org.itstep.liannoi.examandroid.application.storage.users.commands.DeleteCommand
import org.itstep.liannoi.examandroid.application.storage.users.commands.UpdateCommand
import org.itstep.liannoi.examandroid.application.storage.users.queries.DetailQuery
import org.itstep.liannoi.examandroid.application.storage.users.queries.ListQuery

interface UsersRepository : Repository<CreateCommand, CreateCommand.Notification,
        ListQuery, ListQuery.Notification,
        DetailQuery, DetailQuery.Notification,
        UpdateCommand, UpdateCommand.Notification,
        DeleteCommand, DeleteCommand.Notification>,
    Disposable
