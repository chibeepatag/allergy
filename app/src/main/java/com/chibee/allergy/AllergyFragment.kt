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
import com.chibee.allergy.data.AllergyDao
import com.chibee.allergy.databinding.FragmentAllergyBinding
import com.chibee.allergy.viewmodels.AllergyViewModel
import com.chibee.allergy.viewmodels.AllergyViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [AllergyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllergyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAllergyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_allergy, container, false)
        val application = requireNotNull(this.activity).application as AllergyApplication
        val allergyDao: AllergyDao = application.database.allergyDao()
        val arguments = AllergyFragmentArgs.fromBundle(requireArguments())
        val allergyViewModelFactory = AllergyViewModelFactory(allergyDao, arguments.allergyId)
        val allergyViewModel = ViewModelProvider(this, allergyViewModelFactory).get(AllergyViewModel::class.java)
        binding.allergyViewModel = allergyViewModel
        allergyViewModel.navigateToChart.observe(viewLifecycleOwner, Observer {
            if(it == true){
                val toChart = AllergyFragmentDirections.actionAllergyFragmentToChartFragment()
                findNavController().navigate(toChart)
                allergyViewModel.doneNavigatingToChart()
            }
        })
        binding.lifecycleOwner = this
        return binding.root
    }


}