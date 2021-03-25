package com.chibee.allergy.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chibee.allergy.data.Allergy
import com.chibee.allergy.data.AllergyDao
import com.chibee.allergy.data.Reaction

class CreateAllergyViewModel(val allergyDao: AllergyDao, private val patientId: Long): ViewModel(){
    val drugId = MutableLiveData<String>()
    //val drugId: LiveData<Long>
    //    get() = _drugId

    val _takenStart = MutableLiveData<Long>()
    val takenStart: LiveData<Long>
        get() = _takenStart

    val _takenEnd = MutableLiveData<Long>()
    val takenEnd: LiveData<Long>
        get() = _takenEnd

    val _reactionDate = MutableLiveData<Long>()
    val reactionDate: LiveData<Long>
        get() = _reactionDate


    val severity = MutableLiveData<String>()
    //val severity: LiveData<String>
    //    get() = _severity
    //val _systemsInvolved = MutableLiveData<List<Reaction>>()
    //val systemsInvolved: LiveData<List<Reaction>>
    //    get() = _systemsInvolved
    val interventions =  MutableLiveData<String>()
    //val interventions: LiveData<String>
    //    get() = _interventions

    fun onDone(){
        drugId.value?.toString()?.let { Log.i("CreateAllergyViewModel", it) }
        interventions.value?.toString()?.let { Log.i("CreateAllergyViewModel", it) }
        val allergy = Allergy(patientId = patientId)
        allergyDao.insert(allergy)
    }
}