package com.chibee.allergy.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.chibee.allergy.data.AllergyDatabase
import com.chibee.allergy.data.Patient
import com.chibee.allergy.data.PatientDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PatientViewModel(val database: PatientDao, private val patientId: Long): ViewModel() {
    var patient = MediatorLiveData<Patient>()

    val patientName: String?
        get() = patient.value?.patientName

    init {
        patient.addSource(database.getPatient(patientId), patient::setValue)
    }

    private fun initializePatient() {
        viewModelScope.launch {
           // patient = getPatient(patientId)
        }
    }

    suspend private fun getPatient(patientId: Long): LiveData<Patient>{
        return withContext(Dispatchers.IO) {
            val patientCount = database.getPatient(patientId)
            return@withContext patientCount
        }
    }
}