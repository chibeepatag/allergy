package com.chibee.allergy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "allergies",
foreignKeys = [
    ForeignKey(entity = Patient::class,  parentColumns = ["id"], childColumns = ["patient_id"]),
    ForeignKey(entity = Drug::class,     parentColumns = ["id"], childColumns = ["drug_id"]),
    ForeignKey(entity = Reaction::class, parentColumns = ["id"], childColumns = ["reaction_id"])])
data class Allergy(
    @PrimaryKey
    @ColumnInfo(name="id")
    val allergyId: Long,
    @ColumnInfo(name="patient_id")
    val patientId: Long,
    @ColumnInfo(name="drug_id")
    val drugId: Long,
    @ColumnInfo(name="reaction_id")
    val reactionId: Long
) {
}