package com.chibee.allergy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interventions")
data class Intervention(
    @PrimaryKey
    @ColumnInfo(name="id")
    val interventionId: Long,
    val description: String,
    val patient: Long
) {

}