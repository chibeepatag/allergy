package com.chibee.allergy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "allergies",
foreignKeys = [
    ForeignKey(entity = Patient::class,  parentColumns = ["id"], childColumns = ["patient_id"])])
data class Allergy(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val allergyId: Long = 0L,
    @ColumnInfo(name="patient_id")
    val patientId: Long,
    @ColumnInfo(name="drug")
    val drug: String = "",
    @ColumnInfo(name="reaction")
    val reaction: String = "",
    val startTaken: Long = 0L,
    val endTaken: Long = 0L,
    val dateReaction: Long = 0L,
    val severity: String = "",
    val system: String = "",
    val intervention: String = ""
) {
}