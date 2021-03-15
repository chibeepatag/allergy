package com.chibee.allergy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drugs")
data class Drug(
    @PrimaryKey
    @ColumnInfo(name="id")
    var drugId: Long,
    var category: String,
    var name: String
) {
}