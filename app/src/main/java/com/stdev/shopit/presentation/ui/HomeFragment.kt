package com.stdev.shopit.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.stdev.shopit.R
import com.stdev.shopit.data.model.Category2
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.databinding.FragmentHomeBinding
import com.stdev.shopit.presentation.adapter.HomeAdapter
import com.stdev.shopit.presentation.viewmodel.HomeViewModel
//import com.stdev.shopit.presentation.viewmodel.HomeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel : HomeViewModel

    @Inject
    lateinit var adapter : HomeAdapter
    private lateinit var binding : FragmentHomeBinding

    private var category2 = mutableListOf<Category2>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        viewModel.getAllCategories()

        viewModel.categories.observe(viewLifecycleOwner){response ->
            when(response){
                is Resource.Success -> {
                    val categories = response.data
                    val chip = Chip(requireContext())
                    chip.text = "All"
                    chip.id = 0
                    chip.isChecked = true
                    category2.clear()
                    binding.chipGroup.removeAllViews()
                    category2.add(Category2(0,"All"))
                    binding.chipGroup.addView(chip)
                    categories?.forEachIndexed { index, category ->
                        val chip = Chip(requireContext())
                        chip.text = category
                        chip.id = index+1
                        category2.add(Category2(index,category))
                        binding.chipGroup.addView(chip)
                    }

                }
                is Resource.Loading -> {
                    Log.i("HomeFragment","Loading...")
                }
                is Resource.Error -> {
                    Log.i("HomeFragment","${response.message}")
                }
            }
        }

        viewModel.getAllProducts()

        viewModel.products.observe(viewLifecycleOwner){response ->
            when(response){
                is Resource.Success -> {
                    adapter.differ.submitList(response.data)
                    binding.homeRecyclerView.visibility = View.VISIBLE
                    Log.i("HomeFragment","${response.data}")
                }
                is Resource.Loading -> {
                    //binding.homeRecyclerView.visibility = View.INVISIBLE
                    Log.i("HomeFragment","Loading...")
                }
                is Resource.Error -> {
                    Log.i("HomeFragment","${response.message}")
                }
            }
        }

        adapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.homeRecyclerView.adapter = adapter

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            group.isSingleSelection = true
            val chipid = group.checkedChipId
            val category = category2[chipid].category
            val categoryList = viewModel.products.value?.data?.filter {
                it.category == category
            }
            viewModel.getCategoryProducts(category)
//            adapter.differ.submitList(categoryList)
        }

        binding.homeCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        binding.homeProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.homeSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.homeSearchView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

    }



}