package com.chibee.allergy.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.chibee.allergy.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PatientViewModel(val patientDao: PatientDao, val allergyDao: AllergyDao, private val patientId: Long): ViewModel() {


    var patient = MediatorLiveData<Patient>()
    var allergies = MediatorLiveData<List<Allergy>>()
    val patientName: String?
        get() = patient.value?.patientName

    init {
        patient.addSource(patientDao.getPatient(patientId), patient::setValue)
        allergies.addSource(allergyDao.getAllergies(patientId), allergies::setValue)
    }

    fun onAllergyClicked(allergyId: Long) {
        Log.i("Allergy Clicked", allergyId.toString())
    }
}