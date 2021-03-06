package com.chibee.allergy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.chibee.allergy.utilities.DATABASE_NAME
import com.chibee.allergy.utilities.DBSeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Allergy::class, Intervention::class, Patient::class, Reaction::class], version = 1, exportSchema = true)
abstract class AllergyDatabase: RoomDatabase(){
    abstract fun allergyDao(): AllergyDao
    abstract fun interventionDao(): InterventionDao
    abstract fun patientDao(): PatientDao
    abstract fun reactionDao(): ReactionDao

    private class AllergyDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            instance?.let { database ->
                scope.launch {
                    populateDatabase(database.reactionDao())
                }
            }
        }

        suspend fun populateDatabase(reactionDao: ReactionDao) {
            val reactions = DBSeed.getReactions()
            reactionDao.insertAll(reactions)
        }
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: AllergyDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): AllergyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, scope).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, scope: CoroutineScope): AllergyDatabase {
            return Room.databaseBuilder(context, AllergyDatabase::class.java, DATABASE_NAME)
                .addCallback(AllergyDatabaseCallback(scope))
                .build()
        }
    }


}