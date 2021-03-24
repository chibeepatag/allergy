package com.chibee.allergy.utilities

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:birthdate")
fun birthdate(view: TextView, dob: Long){
    val date = Date(dob)
    val format = SimpleDateFormat("yyyy/MM/dd")
    val formattedDate = format.format(date)
    view.setText(formattedDate)
}