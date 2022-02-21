package clever.arinda.kotlinlearnerenrollment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import clever.arinda.kotlinlearnerenrollment.database.daos.RegistrationDatabaseDao
import clever.arinda.kotlinlearnerenrollment.database.entities.RegistrationEntity

@Database(entities = [RegistrationEntity::class], version = 2, exportSchema = false)
abstract class KotlinLearnerEnrollDatabase : RoomDatabase(){

    abstract val registerDatabaseDao: RegistrationDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: KotlinLearnerEnrollDatabase? = null

        fun getInstance(context: Context): KotlinLearnerEnrollDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KotlinLearnerEnrollDatabase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}