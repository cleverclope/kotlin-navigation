package clever.arinda.kotlinlearnerenrollment.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import clever.arinda.kotlinlearnerenrollment.database.entities.RegistrationEntity

@Dao
interface RegistrationDatabaseDao {

    @Insert
    suspend fun insert(register: RegistrationEntity)

    //@Delete
    //suspend  fun deleteSubscriber(register: RegisterEntity):Int

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegistrationEntity>>

//    @Query("DELETE FROM Register_users_table")
//    suspend fun deleteAll(): Int

    @Query("SELECT * FROM Register_users_table WHERE email LIKE :email")
    suspend fun getUserEmail(email: String): RegistrationEntity?

}