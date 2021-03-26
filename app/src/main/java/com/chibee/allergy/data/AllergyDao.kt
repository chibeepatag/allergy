package com.chibee.allergy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AllergyDao {
    @Insert
    fun insert(allergy: Allergy)

    @Query("SELECT * FROM allergies where patient_id = :patientId")
    fun getAllergies(patientId: Long): LiveData<List<Allergy>>

    @Query("SELECT * FROM allergies where id = :allergyId")
    fun getAllergy(allergyId: Long): LiveData<Allergy>
}