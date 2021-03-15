package com.chibee.allergy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chibee.allergy.data.AllergyDatabase
import com.chibee.allergy.data.Patient
import com.chibee.allergy.data.PatientDao
import kotlinx.coroutines.launch

class PatientViewModel(val database: PatientDao): ViewModel() {
    var patient = MutableLiveData<Patient>()

    init {
        initializePatient()
    }

    private fun initializePatient() {
        viewModelScope.launch {
            patient.value = database.getPatient()
        }
    }
}