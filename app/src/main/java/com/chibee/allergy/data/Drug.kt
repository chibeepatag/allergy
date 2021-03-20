package com.chibee.allergy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drugs")
data class Drug(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var drugId: Long = 0L,
    var category: String,
    var name: String
) {
}