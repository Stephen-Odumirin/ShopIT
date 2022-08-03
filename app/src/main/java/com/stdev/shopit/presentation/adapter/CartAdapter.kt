package com.stdev.shopit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stdev.shopit.data.model.*
import com.stdev.shopit.data.util.Utils
import com.stdev.shopit.databinding.CartItemBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<CartItem2>() {
        override fun areItemsTheSame(oldItem: CartItem2, newItem: CartItem2): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartItem2, newItem: CartItem2): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    private var incrementListener : ((CartItem2) -> Unit) = {}

    private var decrementListener : ((CartItem2) -> Unit) = {}

    private var removeListener : ((CartItem2) -> Unit) = {}

    fun setOnRemoveClickListener(listener : (CartItem2) -> Unit){
        removeListener = listener
    }

    fun incrementClickListener(listener : (CartItem2) -> Unit){
        incrementListener = listener
    }

    fun decrementClickListener(listener : (CartItem2) -> Unit){
        decrementListener = listener
    }


    inner class CartViewHolder(private val binding : CartItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(cartItem: CartItem2){

            binding.cartItemName.text = cartItem.title
            binding.cartItemPrice.text = "USD ${Utils.formatPrice(cartItem.price)}"
            binding.cartItemQuantity.text = "${cartItem.quantity}"

            Glide.with(binding.cartItemImage)
                .load(cartItem.image)
                .into(binding.cartItemImage)

            binding.cartItemDelete.setOnClickListener {
                removeListener(cartItem)
            }

            binding.cartItemPlus.setOnClickListener {
                incrementListener(cartItem)
            }

            binding.cartItemMinus.setOnClickListener {
                decrementListener(cartItem)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = differ.currentList[position]
        holder.bindData(cartItem)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}