package com.chibee.allergy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chibee.allergy.data.AllergyDao

class AllergyViewModelFactory(private val allergyDao: AllergyDao, private val allergyId: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllergyViewModel::class.java)) {
            return AllergyViewModel(allergyDao, allergyId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}