package com.chibee.allergy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chibee.allergy.data.AllergyDao
import com.chibee.allergy.databinding.FragmentCreateAllergyBinding
import com.chibee.allergy.viewmodels.CreateAllergyViewModel
import com.chibee.allergy.viewmodels.CreateAllergyViewModelFactory


class CreateAllergyFragment : Fragment() {

    private var _binding: FragmentCreateAllergyBinding? = null
    private val binding get() = _binding!!
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
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CreateAllergyViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
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