package com.chibee.allergy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PatientDao {
    @Query("SELECT * FROM patients")
    fun getPatients(): LiveData<List<Patient>>

    @Query("SELECT * FROM patients ORDER BY id DESC LIMIT 1")
    fun getPatient(): Patient

    @Insert
    fun insert(patient: Patient)
}