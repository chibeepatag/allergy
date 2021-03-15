package com.chibee.allergy.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chibee.allergy.data.PatientDao

class SetupViewModelFactory(private val datasource: PatientDao) :  ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetupViewModel::class.java)) {
            return SetupViewModel(datasource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}