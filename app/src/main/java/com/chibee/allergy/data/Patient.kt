package com.chibee.allergy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class Patient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var patientId: Long = 0L,
    val patientName: String,
    val dateOfBirth: Long,
    val sex: String
) {
}