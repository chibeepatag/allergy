package com.chibee.allergy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.chibee.allergy.utilities.DATABASE_NAME
import com.chibee.allergy.workers.SeedDatabaseWorker

@Database(entities = [Allergy::class, Drug::class, Intervention::class, Patient::class, Reaction::class], version = 1, exportSchema = true)
abstract class AllergyDatabase: RoomDatabase(){
    abstract fun allergyDao(): AllergyDao
    abstract fun drugDao(): DrugDao
    abstract fun interventionDao(): InterventionDao
    abstract fun patientDao(): PatientDao
    abstract fun reactionDao(): ReactionDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: AllergyDatabase? = null

        fun getInstance(context: Context): AllergyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AllergyDatabase {
            return Room.databaseBuilder(context, AllergyDatabase::class.java, DATABASE_NAME)
                .createFromAsset("allergy-db.db")
                .build()
        }
    }
}