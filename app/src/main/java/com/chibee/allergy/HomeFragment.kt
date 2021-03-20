package com.chibee.allergy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chibee.allergy.data.AllergyDatabase
import com.chibee.allergy.data.PatientDao
import com.chibee.allergy.databinding.FragmentHomeBinding
import com.chibee.allergy.viewmodels.HomeViewModel
import com.chibee.allergy.viewmodels.HomeViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val application = requireNotNull(this.activity).application as AllergyApplication

        val datasource = application.database
        val viewModelFactory = HomeViewModelFactory(datasource.patientDao())
        val homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        binding.informationBtn.setOnClickListener {
            val toInformation = HomeFragmentDirections.actionHomeFragmentToPatientFragment(
                homeViewModel.patient.value!!.patientId
            )
           findNavController().navigate(toInformation)
        }

        binding.alertsBtn.setOnClickListener{
            val toAlertFragment = HomeFragmentDirections.actionHomeFragmentToAlertFragment()
            findNavController().navigate(toAlertFragment)
        }

        return binding.root
    }


}