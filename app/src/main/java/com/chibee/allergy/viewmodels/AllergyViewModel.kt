package com.chibee.allergy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chibee.allergy.data.Allergy
import com.chibee.allergy.data.AllergyDao

class AllergyViewModel(val allergyDao: AllergyDao, private val allergyId: Long): ViewModel() {

    val allergy = MediatorLiveData<Allergy>()

    val _navigateToChart = MutableLiveData<Boolean>()
    val navigateToChart: LiveData<Boolean>
        get() = _navigateToChart

    fun doneNavigatingToChart(){
        _navigateToChart.value = null
    }

    fun onChart(){
        _navigateToChart.value = true
    }
    init{
        allergy.addSource(allergyDao.getAllergy(allergyId), allergy::setValue)
    }
}