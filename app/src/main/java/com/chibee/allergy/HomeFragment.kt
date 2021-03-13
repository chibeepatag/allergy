package com.chibee.allergy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.chibee.allergy.databinding.FragmentHomeBinding


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
        binding.informationBtn.setOnClickListener {
            val toInformation = HomeFragmentDirections.actionHomeFragmentToPatientFragment()
            findNavController().navigate(toInformation)
        }

        binding.alertsBtn.setOnClickListener{
            val toAlertFragment = HomeFragmentDirections.actionHomeFragmentToAlertFragment()
            findNavController().navigate(toAlertFragment)
        }
        return binding.root
    }


}