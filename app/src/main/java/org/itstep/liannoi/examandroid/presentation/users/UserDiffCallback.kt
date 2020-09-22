package org.itstep.liannoi.examandroid.presentation.users

import androidx.recyclerview.widget.DiffUtil
import org.itstep.liannoi.examandroid.application.storage.models.User

class UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem == newItem
}
