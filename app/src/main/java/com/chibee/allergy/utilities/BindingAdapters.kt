package com.chibee.allergy.utilities

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.time.LocalDate
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
@BindingAdapter("app:age")
fun ageFromDate(view: TextView, dob: Long){
    val birthdate = Calendar.getInstance();
    birthdate.timeInMillis = dob
    val today = Calendar.getInstance()
    var age = today.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR)
    if (today.get(Calendar.DAY_OF_YEAR) < birthdate.get(Calendar.DAY_OF_YEAR)) {
        age--;
    }
    view.setText("${age.toString()} y.o.")
}