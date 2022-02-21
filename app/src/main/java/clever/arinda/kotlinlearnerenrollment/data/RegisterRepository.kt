package clever.arinda.kotlinlearnerenrollment.data

import clever.arinda.kotlinlearnerenrollment.database.daos.RegistrationDatabaseDao
import clever.arinda.kotlinlearnerenrollment.database.entities.RegistrationEntity

class RegisterRepository(private val dao: RegistrationDatabaseDao){

    val users = dao.getAllUsers()

    suspend fun insert(user: RegistrationEntity) {
        return dao.insert(user)
    }

    suspend fun getUserEmail(email: String):RegistrationEntity?{
        return dao.getUserEmail(email)
    }
}