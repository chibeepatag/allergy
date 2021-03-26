package com.chibee.allergy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chibee.allergy.data.AllergyDao
import com.chibee.allergy.data.AllergyDatabase
import com.chibee.allergy.data.PatientDao
import com.chibee.allergy.databinding.FragmentPatientBinding
import com.chibee.allergy.viewmodels.HomeViewModelFactory
import com.chibee.allergy.viewmodels.PatientViewModel
import com.chibee.allergy.viewmodels.PatientViewModelFactory


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
        val application = requireNotNull(this.activity).application as AllergyApplication

        val patientDao: PatientDao = application.database.patientDao()
        val allergyDao: AllergyDao = application.database.allergyDao()
        val arguments = PatientFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = PatientViewModelFactory(patientDao, allergyDao, arguments.patientId)
        val patientViewModel = ViewModelProvider(this, viewModelFactory).get(PatientViewModel::class.java)
        binding.patientViewModel = patientViewModel

        binding.addAllergy.setOnClickListener {
            val toAddAllergy =
                PatientFragmentDirections.actionPatientFragmentToCreateAllergyFragment(
                    patientViewModel.patient.value!!.patientId
                )
            findNavController().navigate(toAddAllergy)
        }

        binding.allergyCards.layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        val adapter = AllergyCardRecycleViewAdapter(AllergyListener { allergyId ->
            patientViewModel.onAllergyClicked(allergyId)
        })
        binding.allergyCards.adapter = adapter

        patientViewModel.allergies.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.launchSubmitList(it)
            }
        })

        patientViewModel.navigateToAllergy.observe(viewLifecycleOwner, Observer { allergyId ->
            allergyId?.let{
                val toAllergy = PatientFragmentDirections.actionPatientFragmentToAllergyFragment(allergyId)
                findNavController().navigate(toAllergy)
                patientViewModel.allergyNavigated()
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }

}