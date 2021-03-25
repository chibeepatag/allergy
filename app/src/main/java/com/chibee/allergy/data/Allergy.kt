package com.chibee.allergy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "allergies",
foreignKeys = [
    ForeignKey(entity = Patient::class,  parentColumns = ["id"], childColumns = ["patient_id"]),
    ForeignKey(entity = Reaction::class, parentColumns = ["id"], childColumns = ["reaction_id"])])
data class Allergy(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val allergyId: Long = 0L,
    @ColumnInfo(name="patient_id")
    val patientId: Long,
    @ColumnInfo(name="drug")
    val drug: String = "",
    @ColumnInfo(name="reaction_id")
    val reactionId: Long? = 0L
) {
}