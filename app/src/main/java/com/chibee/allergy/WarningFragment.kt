package com.chibee.allergy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.chibee.allergy.databinding.FragmentWarningBinding


/**
 * A simple [Fragment] subclass.
 * Use the [WarningFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WarningFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentWarningBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_warning, container, false)
        binding.warningAcceptBtn.setOnClickListener {
            val tohome = WarningFragmentDirections.actionWarningFragmentToHomeFragment()
            findNavController().navigate(tohome)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}