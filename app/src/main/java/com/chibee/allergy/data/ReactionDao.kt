package com.chibee.allergy.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(reactions: List<Reaction>)

    @Query("select * from reactions")
    suspend fun getAll(): List<Reaction>
}