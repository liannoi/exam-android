package org.itstep.liannoi.examandroid.presentation.common.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.itstep.liannoi.examandroid.application.storage.models.User
import org.itstep.liannoi.examandroid.presentation.users.UsersAdapter

@BindingAdapter("app:items")
fun RecyclerView.adapt(items: List<User>?) {
    items?.let { (adapter as UsersAdapter).submitList(items) }
}
