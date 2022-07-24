package com.alexdoff.productcrudunix.ui.product.get

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.alexdoff.productcrudunix.data.obj.Product
import com.alexdoff.productcrudunix.data.obj.ProductParcel
import com.alexdoff.productcrudunix.databinding.FragmentProductSummaryBinding
import com.google.android.flexbox.FlexboxLayout

/**
 * [RecyclerView.Adapter] that can display a [List<Product>].
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
        private val clickableText: FlexboxLayout = binding.textPart


        fun bind(p: Product) {
            namaProduk.text = p.name
            hargaProduk.text = p.price
            clickableText.setOnClickListener { view ->
                val pp = ProductParcel(
                    p.createdAt,
                    p.description,
                    p.name,
                    p.pid,
                    p.price,
                    p.updatedAt
                )
                val action = ProductsFragmentDirections.actionProductsFragmentToProductDetail(pp)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

}