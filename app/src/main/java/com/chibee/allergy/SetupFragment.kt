package com.chibee.allergy

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chibee.allergy.databinding.FragmentSetupBinding
import com.chibee.allergy.viewmodels.SetupViewModel
import com.chibee.allergy.viewmodels.SetupViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [SetupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetupFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0

    lateinit var viewModel: SetupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSetupBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_setup, container, false)
        val application = requireNotNull(this.activity).application as AllergyApplication
        val database = application.database.patientDao()
        val viewModelFactory =  SetupViewModelFactory(database)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SetupViewModel::class.java)
        binding.setupViewModel = viewModel

        viewModel.navigateToPatient.observe(viewLifecycleOwner,  Observer{ navigate ->
            if(navigate){
                val toPatient = SetupFragmentDirections.actionSetupFragmentToPatientFragment(2)
                findNavController().navigate(toPatient)
                viewModel.doneNavigating()
            }
        })
        viewModel.patientDob.observe(viewLifecycleOwner, Observer {
            binding.editTextPatientDob.setText(it.toString())
        })
        pickDate(binding)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    private fun pickDate(binding: FragmentSetupBinding){
        binding.editTextPatientDob.setOnClickListener{
            val cal: Calendar = Calendar.getInstance()
            day = cal.get(Calendar.DAY_OF_MONTH)
            month = cal.get(Calendar.MONTH)
            year = cal.get(Calendar.YEAR)

            DatePickerDialog(requireContext(), R.style.spinnerDatePickerStyle, this, year, month, day).show()
        }
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val cal: Calendar = Calendar.getInstance()
        cal.set(year, month, dayOfMonth)
        viewModel.onSetDob(cal.timeInMillis)
    }
}