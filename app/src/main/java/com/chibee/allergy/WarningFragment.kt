package com.chibee.allergy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chibee.allergy.data.AllergyDatabase
import com.chibee.allergy.databinding.FragmentWarningBinding
import com.chibee.allergy.viewmodels.HomeViewModelFactory
import com.chibee.allergy.viewmodels.WarningViewModel
import com.chibee.allergy.viewmodels.WarningViewModelFactory


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
        val application = requireNotNull(this.activity).application as AllergyApplication
        val datasource = application.database.patientDao()
        val viewModelFactory = WarningViewModelFactory(datasource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(WarningViewModel::class.java)

        viewModel.navigateToHome.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(WarningFragmentDirections.actionWarningFragmentToHomeFragment())
                viewModel.doneNavigating()
            }
        })

        viewModel.navigateToSetup.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(WarningFragmentDirections.actionWarningFragmentToSetupFragment())
                viewModel.doneNavigating()
            }
        })
        binding.warningAcceptBtn.setOnClickListener{
            viewModel.onAccept()
        }
        binding.lifecycleOwner = this
        return binding.root
    }


}