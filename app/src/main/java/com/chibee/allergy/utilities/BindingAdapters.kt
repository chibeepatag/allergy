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

@BindingAdapter(value = ["app:takenStart", "app:takenEnd"])
fun drugDateRange(view: TextView, takenStart: Long?, takenEnd: Long?){
    if(takenStart != null && takenEnd != null){
        val start = Date(takenStart!!)
        val end = Date(takenEnd!!)
        val format = SimpleDateFormat("yyyy/MM/dd")
        val formattedDateStart = format.format(start)
        val formattedDateEnd = format.format(end)
        view.setText("$formattedDateStart - $formattedDateEnd")
    }
}