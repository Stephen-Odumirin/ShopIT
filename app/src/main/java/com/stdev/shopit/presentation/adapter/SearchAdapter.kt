package com.stdev.shopit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stdev.shopit.data.model.ShopItem
import com.stdev.shopit.databinding.CartItemBinding
import com.stdev.shopit.databinding.SearchItemBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

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

    fun setOnItemClickListener(listener : (ShopItem)-> Unit){
        onItemClickListener = listener
    }


    inner class SearchViewHolder(private val binding : SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(shopItem: ShopItem){

            Glide.with(binding.searchItemImage)
                .load(shopItem.image)
                .into(binding.searchItemImage)

            binding.searchItemTitle.text = shopItem.title
            binding.searchItemPrice.text = "USD ${shopItem.price}"
            binding.searchItemRating.text = "${shopItem.rating.rate}"
            binding.searchItemReview.text = "${shopItem.rating.count} Reviews"

            binding.searchItemView.setOnClickListener {
                onItemClickListener(shopItem)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val shopItem = differ.currentList[position]
        holder.bindData(shopItem)
    }

    override fun getItemCount() = differ.currentList.size
}