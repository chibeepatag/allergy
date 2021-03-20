package com.chibee.allergy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chibee.allergy.data.AllergyDatabase
import com.chibee.allergy.data.Patient
import com.chibee.allergy.databinding.FragmentSetupBinding
import com.chibee.allergy.viewmodels.SetupViewModel
import com.chibee.allergy.viewmodels.SetupViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [SetupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetupFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSetupBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_setup, container, false)

        val application = requireNotNull(this.activity).application as AllergyApplication
        val database = application.database.patientDao()
        val viewModelFactory =  SetupViewModelFactory(database)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(SetupViewModel::class.java)
        binding.setupViewModel = viewModel

        viewModel.navigateToPatient.observe(viewLifecycleOwner,  Observer{ navigate ->
            if(navigate){
                val toPatient = SetupFragmentDirections.actionSetupFragmentToPatientFragment(2)
                findNavController().navigate(toPatient)
                viewModel.doneNavigating()
            }

        })
        binding.setLifecycleOwner(this)

        return binding.root
    }
}