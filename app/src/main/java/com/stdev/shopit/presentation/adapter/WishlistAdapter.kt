package com.stdev.shopit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stdev.shopit.data.model.ShopItem
import com.stdev.shopit.databinding.SingleItemBinding
import com.stdev.shopit.databinding.SingleWishlistBinding

class WishlistAdapter : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ShopItem>() {
        override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    private var onItemClickListener : ((ShopItem)-> Unit) = {}
    private var onItemDeleteListener : ((ShopItem)-> Unit) = {}

    fun setOnItemClickListener(listener : (ShopItem)-> Unit){
        onItemClickListener = listener
    }

    fun setOnItemDeleteListener(listener : (ShopItem)-> Unit){
        onItemDeleteListener = listener
    }

    inner class WishlistViewHolder(private val binding : SingleWishlistBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(shopItem: ShopItem){

            Glide.with(binding.itemImage)
                .load(shopItem.image)
                .into(binding.itemImage)

            binding.itemTitle.text = shopItem.title
            binding.itemPrice.text = "USD ${shopItem.price}"
            binding.itemRating.text = "${shopItem.rating.rate}"
            binding.itemReview.text = "${shopItem.rating.count} Reviews"

            binding.itemView.setOnClickListener {
                onItemClickListener(shopItem)
            }

            binding.itemDelete.setOnClickListener {
                onItemDeleteListener(shopItem)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val binding = SingleWishlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WishlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val shopItem = differ.currentList[position]
        holder.bindData(shopItem)
    }

    override fun getItemCount() =  differ.currentList.size


}