package com.chibee.allergy

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chibee.allergy.data.AllergyDao
import com.chibee.allergy.databinding.FragmentCreateAllergyBinding
import com.chibee.allergy.viewmodels.CreateAllergyViewModel
import com.chibee.allergy.viewmodels.CreateAllergyViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


class CreateAllergyFragment : Fragment() {

    private var _binding: FragmentCreateAllergyBinding? = null
    private val binding get() = _binding!!

    private var viewModel: CreateAllergyViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_allergy, container, false)
        val application = requireNotNull(this.activity).application as AllergyApplication

        val allergyDao: AllergyDao = application.database.allergyDao()
        val arguments = CreateAllergyFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = CreateAllergyViewModelFactory(allergyDao, arguments.patientId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CreateAllergyViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupDates()

        return binding.root
    }
    private fun setupDates(){
        val cal: Calendar = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        var supportFragmentManager = requireNotNull(this.activity).getSupportFragmentManager()
        binding.dateTakenEditText.setOnClickListener{
            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                    .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                    .setTitleText("Drug Taken On")
                    .build()
            dateRangePicker.addOnPositiveButtonClickListener {
                if ( it.first != null && it.second != null){
                    val start = it.first
                    val end = it.second
                    viewModel!!.onSetDateTaken(start!!, end!!)
                }
            }

            dateRangePicker.show(supportFragmentManager, "drugDatesTaken")
        }
        binding.dateReactionEditText.setOnClickListener{
            val reactionDatePicker = MaterialDatePicker.Builder.datePicker()
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .setTitleText("Reaction Dates")
                .build()
            reactionDatePicker.addOnPositiveButtonClickListener {
                viewModel?.onSetReactionDate(it)
            }
            reactionDatePicker.show(supportFragmentManager, "reactionDate")
        }
    }
    override fun onResume() {
        super.onResume()
        val drugs = resources.getStringArray(R.array.drugs)
        val arrayDrugAdapter = ArrayAdapter(requireContext(), R.layout.drug_dropdown_item, drugs)
        binding.drugAutoCompleteTextView.setAdapter(arrayDrugAdapter)

        val systems = resources.getStringArray(R.array.systems)
        val arraySystemAdapter = ArrayAdapter(requireContext(), R.layout.drug_dropdown_item, systems)
        binding.systemAutoCompleteTextView.setAdapter(arraySystemAdapter)

        val reactions = resources.getStringArray(R.array.reactions)
        val arrayReactionsAdapter = ArrayAdapter(requireContext(), R.layout.drug_dropdown_item, reactions)
        binding.reactionAutoCompleteTextView.setAdapter(arrayReactionsAdapter)

        val severity = resources.getStringArray(R.array.severity)
        val arraySeverityAdapter = ArrayAdapter(requireContext(), R.layout.drug_dropdown_item, severity)
        binding.severityAutoCompleteTextView.setAdapter(arraySeverityAdapter)

        resources.getStringArray(R.array.severity)
    }
}