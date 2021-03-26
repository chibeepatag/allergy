package com.chibee.allergy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chibee.allergy.data.AllergyDao
import com.chibee.allergy.data.PatientDao

class PatientViewModelFactory (private val patientDao: PatientDao, private val allergyDao: AllergyDao, private val patientId: Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            return PatientViewModel(patientDao, allergyDao, patientId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}