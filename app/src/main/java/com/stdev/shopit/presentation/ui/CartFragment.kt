package com.stdev.shopit.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stdev.shopit.R
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.data.util.Utils
import com.stdev.shopit.databinding.FragmentCartBinding
import com.stdev.shopit.presentation.adapter.CartAdapter
import com.stdev.shopit.presentation.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment(){

    private lateinit var binding : FragmentCartBinding
    @Inject
    lateinit var cartViewModel: CartViewModel
    @Inject
    lateinit var cartAdapter: CartAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartBinding.bind(view)

        cartViewModel.getCartItems().observe(viewLifecycleOwner){
            cartAdapter.differ.submitList(it)
        }

        cartAdapter.setOnRemoveClickListener {
            cartViewModel.deleteCart(it)
        }

        cartAdapter.incrementClickListener {
            Log.i("CartFragment","I don click the increment shit")
            cartViewModel.increment(it)
        }

        cartAdapter.decrementClickListener {
            Log.i("CartFragment","I don click the decrement shit")
            cartViewModel.decrement(it)
        }

        cartViewModel.totalItems.observe(viewLifecycleOwner){
            binding.cartItemsInfo.text = "Total $it Items"
        }

        cartViewModel.totalItemsPrice.observe(viewLifecycleOwner){
            binding.cartItemsPrice.text = "USD ${Utils.formatPrice(it.toString())}"
        }

        binding.cartRecyclerView.adapter = cartAdapter

        binding.cartBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.cartClearAll.setOnClickListener {
            cartViewModel.clearCart()
        }

    }

}