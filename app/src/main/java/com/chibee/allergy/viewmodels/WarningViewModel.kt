package com.chibee.allergy.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chibee.allergy.data.PatientDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WarningViewModel(private val database: PatientDao) : ViewModel() {

    private val _navigateToSetup = MutableLiveData<Boolean>()
    val navigateToSetup: LiveData<Boolean?>
        get() = _navigateToSetup

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome

    fun onAccept(){
        Log.i("Warning view model", "clicked Accept")
        viewModelScope.launch {
            val count = getPatientCount()
            if (count > 0){
                _navigateToHome.value = true
                _navigateToSetup.value = false
            }else{
                _navigateToHome.value = false
                _navigateToSetup.value = true
            }
        }
        Log.i("WarningViewModel", navigateToHome.value.toString())
        Log.i("WarningViewModel", navigateToSetup.value.toString())
    }

    suspend private fun getPatientCount(): Int {
        return withContext(Dispatchers.IO) {
            val patientCount = database.getPatientCount()
            return@withContext patientCount
        }

    }

    fun doneNavigating(){
        _navigateToHome.value = null
        _navigateToSetup.value = null
    }
}