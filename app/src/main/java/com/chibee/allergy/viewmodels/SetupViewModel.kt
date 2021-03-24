package com.chibee.allergy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chibee.allergy.data.Patient
import com.chibee.allergy.data.PatientDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SetupViewModel(private val database: PatientDao): ViewModel() {
    val patientName = MutableLiveData<String>()
    val patientSex = MutableLiveData<String>()

    private val _patientDob = MutableLiveData<Long>()
    val patientDob: LiveData<Long>
        get() = _patientDob

    private val _navigateToPatient = MutableLiveData<Boolean>()
    val navigateToPatient: LiveData<Boolean>
        get() = _navigateToPatient

    init{
        patientSex.value = "Male"
    }
    fun onDone(){
        viewModelScope.launch {
            val patient = Patient(patientName = patientName.value!!, dateOfBirth = patientDob.value!!, sex = patientSex.value!!)
            insert(patient)
            _navigateToPatient.value = true
        }
    }
    suspend fun insert(patient: Patient){
        withContext(Dispatchers.IO) {
            database.insert(patient)
        }
    }

    fun doneNavigating() {
        _navigateToPatient.value = false
    }

    fun onClickMale(){
        patientSex.value = "Male"
    }

    fun onClickFemale(){
        patientSex.value = "Female"
    }

    fun onSetDob(timeInMills: Long){
        _patientDob.value = timeInMills
    }
}