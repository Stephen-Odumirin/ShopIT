package com.stdev.shopit.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.stdev.shopit.R
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.presentation.viewmodel.HomeViewModel
import com.stdev.shopit.presentation.viewmodel.HomeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: HomeViewModelFactory
    lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]

        viewModel.getAllProducts()

        viewModel.products.observe(viewLifecycleOwner){response ->
            when(response){
                is Resource.Success -> {
                    Log.i("HomeFragment","${response.data}")
                }
                is Resource.Loading -> {
                    Log.i("HomeFragment","Loading...")
                }
                is Resource.Error -> {
                    Log.i("HomeFragment","${response.message}")
                }
            }
        }

    }



}