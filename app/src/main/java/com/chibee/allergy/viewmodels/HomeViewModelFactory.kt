package com.chibee.allergy.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chibee.allergy.data.PatientDao

class HomeViewModelFactory (
    private val dataSource: PatientDao, private val patientId: Long) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(dataSource, patientId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}

