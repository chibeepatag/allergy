package com.chibee.allergy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reactions")
data class Reaction(
    @PrimaryKey
    @ColumnInfo(name="id")
    var reactionId: Long = 0L,
    val system: String,
    val reaction: String
) {
}