package clever.arinda.kotlinlearnerenrollment.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Register_users_table")
data class RegistrationEntity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "fullname")
    var fullname: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "password_text")
    var password: String
)