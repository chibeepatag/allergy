package com.chibee.allergy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chibee.allergy.data.AllergyDao
import com.chibee.allergy.databinding.FragmentCreateAllergyBinding
import com.chibee.allergy.viewmodels.CreateAllergyViewModel
import com.chibee.allergy.viewmodels.CreateAllergyViewModelFactory


class CreateAllergyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCreateAllergyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_allergy, container, false)
        val application = requireNotNull(this.activity).application as AllergyApplication

        val allergyDao: AllergyDao = application.database.allergyDao()
        val arguments = CreateAllergyFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = CreateAllergyViewModelFactory(allergyDao, arguments.patientId)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CreateAllergyViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
    }


}