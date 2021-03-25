package com.chibee.allergy.utilities

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.chibee.allergy.viewmodels.CreateAllergyViewModel
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:dateFromLong")
fun dateFromLong(view: TextView, dob: Long){
    val date = Date(dob)
    val format = SimpleDateFormat("yyyy/MM/dd")
    val formattedDate = format.format(date)
    view.setText(formattedDate)
}

@BindingAdapter("app:drugDateRange")
fun drugDateRange(view: TextView, viewModel: CreateAllergyViewModel){
    if(viewModel.takenStart.value != null && viewModel.takenEnd.value != null){
        val start = Date(viewModel.takenStart.value!!)
        val end = Date(viewModel.takenEnd.value!!)
        val format = SimpleDateFormat("yyyy/MM/dd")
        val formattedDateStart = format.format(start)
        val formattedDateEnd = format.format(end)
        view.setText("$formattedDateStart - $formattedDateEnd")
    }
}