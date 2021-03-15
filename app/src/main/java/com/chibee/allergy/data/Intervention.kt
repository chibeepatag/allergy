package com.chibee.allergy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interventions")
data class Intervention(
    @PrimaryKey
    val interventionId: Long,
    val description: String
) {

}