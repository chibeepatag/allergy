package com.chibee.allergy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chibee.allergy.data.PatientDao

class PatientViewModelFactory (private val dataSource: PatientDao, private val patientId: Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            return PatientViewModel(dataSource, patientId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}