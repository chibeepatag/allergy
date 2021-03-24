package com.chibee.allergy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chibee.allergy.data.AllergyDao

class CreateAllergyViewModelFactory(private val allergyDao: AllergyDao, private val patientId: Long) :  ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateAllergyViewModel::class.java)) {
            return CreateAllergyViewModel(allergyDao, patientId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}