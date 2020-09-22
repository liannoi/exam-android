package org.itstep.liannoi.examandroid.application.storage.users.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User constructor(
    @ColumnInfo(name = "FirstName") val firstName: String = "",
    @ColumnInfo(name = "LastName") val lastName: String = "",
    @ColumnInfo(name = "Salary") val salary: Int = 0,
    @ColumnInfo(name = "UserId") @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    val formattedSalary: String
        get() = "$salary $"
}
