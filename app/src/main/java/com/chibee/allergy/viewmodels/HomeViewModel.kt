package com.chibee.allergy.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chibee.allergy.data.Patient
import com.chibee.allergy.data.PatientDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val database: PatientDao): ViewModel() {
    var patient = MutableLiveData<Patient>()

    init {
        initializePatient()
    }

    private fun initializePatient() {
        viewModelScope.launch {
            patient.value = getPatient()
        }
    }

    suspend private fun getPatient(): Patient {
        return withContext(Dispatchers.IO) {
            var _patient = database.getPatient()
            return@withContext _patient
        }
    }
}