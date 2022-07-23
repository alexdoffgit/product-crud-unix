package com.alexdoff.productcrudunix.ui.product.get

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.alexdoff.productcrudunix.data.obj.Product
import com.alexdoff.productcrudunix.databinding.FragmentProductSummaryBinding

import com.alexdoff.productcrudunix.ui.product.get.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ProductsRecyclerViewAdapter(
    private val values: List<Product>
) : RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProductSummaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentProductSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val namaProduk: TextView = binding.namaProduk
        private val hargaProduk: TextView = binding.hargaProduk

        fun bind(p: Product) {
            namaProduk.text = p.name
            hargaProduk.text = p.price
        }
    }

}