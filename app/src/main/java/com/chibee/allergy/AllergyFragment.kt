package com.chibee.allergy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.chibee.allergy.databinding.FragmentAllergyBinding


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
        binding.chartBtn.setOnClickListener{
            val toChart = AllergyFragmentDirections.actionAllergyFragmentToChartFragment()
            findNavController().navigate(toChart)
        }
        return binding.root
    }


}