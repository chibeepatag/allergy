package com.chibee.allergy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.chibee.allergy.databinding.FragmentPatientBinding



/**
 * A simple [Fragment] subclass.
 * Use the [PatientFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PatientFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPatientBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_patient, container, false)
        binding.addAllergy.setOnClickListener{
            val toAddAllergy = PatientFragmentDirections.actionPatientFragmentToCreateAllergyFragment()
            findNavController().navigate(toAddAllergy)
        }
        //replace with view model
        binding.textView10.setOnClickListener{
            val toAllergy = PatientFragmentDirections.actionPatientFragmentToAllergyFragment()
            findNavController().navigate(toAllergy)

        }
        return binding.root
    }

}