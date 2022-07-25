package com.alexdoff.productcrudunix.ui.product.get

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import com.alexdoff.productcrudunix.data.model.ProductViewModel
import com.alexdoff.productcrudunix.data.obj.Product
import com.alexdoff.productcrudunix.data.obj.ProductParcel
import com.alexdoff.productcrudunix.databinding.FragmentProductSummaryBinding
import com.google.android.flexbox.FlexboxLayout

/**
 * [RecyclerView.Adapter] that can display a [List<Product>].
 * TODO: Replace the implementation with code for your data type.
 */
class ProductsRecyclerViewAdapter(
    private val values: List<Product>,
    private val vm: ProductViewModel
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
        private val deleteButton: Button = binding.deleteProduct
        private val updateButton: Button = binding.goToUpdate


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

            updateButton.setOnClickListener {
                val pp = ProductParcel(
                    p.createdAt,
                    p.description,
                    p.name,
                    p.pid,
                    p.price,
                    p.updatedAt
                )
                val action = ProductsFragmentDirections.actionProductsFragmentToProductEdit(pp)
                Navigation.findNavController(it).navigate(action)
            }

            deleteButton.setOnClickListener {
                vm.deleteProduct(p.pid)

                notifyItemRemoved(bindingAdapterPosition)
                notifyItemRangeChanged(bindingAdapterPosition, values.size)
                val nc = Navigation.findNavController(it)
                val thisPage = nc.currentDestination?.id
                nc.popBackStack()
                nc.navigate(thisPage!!)

                // TODO: Reliable way to check network fail
//                if(vm.success.value == true) {
//                    vm.success.value = false
//                    notifyItemRemoved(bindingAdapterPosition)
//                    notifyItemRangeChanged(bindingAdapterPosition, values.size)
//                    val nc = Navigation.findNavController(it)
//                    val thisPage = nc.currentDestination?.id
//                    nc.popBackStack()
//                    nc.navigate(thisPage!!)
//                }
            }
        }
    }

}