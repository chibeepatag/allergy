package com.chibee.allergy.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chibee.allergy.data.Allergy
import com.chibee.allergy.data.AllergyDao
import com.chibee.allergy.data.Reaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateAllergyViewModel(val allergyDao: AllergyDao, private val patientId: Long): ViewModel(){
    val drug = MutableLiveData<String>()

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
    val system = MutableLiveData<String>()
    val reaction =  MutableLiveData<String>()
    val interventions =  MutableLiveData<String>()

    private val _navigateBackToPatient = MutableLiveData<Boolean>()
    val navigateBackToPatient: LiveData<Boolean>
        get() = _navigateBackToPatient


    fun onDone(){
        drug.value?.let{
            Log.i("CreateAllergyViewModel", it)
        }
        interventions.value?.toString()?.let { Log.i("CreateAllergyViewModel", it) }
        val allergy = Allergy(
            patientId = patientId,
            drug = drug.value!!,
            severity = severity.value!!,
            system = system.value!!,
            reaction = reaction.value!!,
            intervention =  interventions.value!!,
            startTaken = takenStart.value!!,
            endTaken = takenEnd.value!!,
            dateReaction = reactionDate.value!!
        )
        viewModelScope.launch {
            insert(allergy)
        }
    }

    suspend fun insert(allergy: Allergy){
        withContext(Dispatchers.IO){
            allergyDao.insert(allergy)
            _navigateBackToPatient.postValue(true)
        }
    }

    fun onSetReactionDate(timeInMills: Long){
        _reactionDate.value = timeInMills
    }

    fun onSetDateTaken(start: Long, end: Long){
        _takenStart.value = start
        _takenEnd.value = end
    }

    fun doneNavigating(){
        _navigateBackToPatient.value = false
    }
}