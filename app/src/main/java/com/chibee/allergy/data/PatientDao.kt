package com.chibee.allergy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PatientDao {
    @Query("SELECT * FROM patients")
    fun getPatients(): LiveData<List<Patient>>

    @Query("SELECT * FROM patients WHERE id = :id")
    fun getPatient(id: Long): Patient

    @Insert
    fun insert(patient: Patient)

    @Query("SELECT COUNT(*) FROM PATIENTS")
    fun getPatientCount(): Int
}