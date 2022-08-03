package com.stdev.shopit.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stdev.shopit.R
import com.stdev.shopit.databinding.FragmentWishlistBinding
import com.stdev.shopit.presentation.adapter.WishlistAdapter
import com.stdev.shopit.presentation.viewmodel.WishlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WishlistFragment : Fragment() {

    private lateinit var binding : FragmentWishlistBinding

    @Inject
    lateinit var viewModel : WishlistViewModel

    private lateinit var adapter: WishlistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wishlist,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = WishlistAdapter()

        binding = FragmentWishlistBinding.bind(view)

        binding.wishlistBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.wishlistRecyclerView.adapter = adapter

        adapter.setOnItemClickListener {
            val action = WishlistFragmentDirections.actionWishlistFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }

        adapter.setOnItemDeleteListener {
            viewModel.deleteWishlist(it)
        }

        viewModel.getWishlist().observe(viewLifecycleOwner){shopItems ->
            adapter.differ.submitList(shopItems)
        }

        binding.wishlistDelete.setOnClickListener {
            viewModel.clearWishlist()
        }



    }

}