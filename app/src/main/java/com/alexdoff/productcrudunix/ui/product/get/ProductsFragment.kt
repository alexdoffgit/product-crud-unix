package com.alexdoff.productcrudunix.ui.product.get

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.alexdoff.productcrudunix.R
import com.alexdoff.productcrudunix.data.model.ProductViewModel
import com.alexdoff.productcrudunix.databinding.FragmentProductListBinding

/**
 * A fragment representing a list of Items.
 */
class ProductsFragment : Fragment() {
    private var columnCount = 1
    private lateinit var productViewModel: ProductViewModel
    private var _binding: FragmentProductListBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root

        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
        productViewModel.getAllProduct()

        // Set the adapter
        with(binding.productList) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            productViewModel.products.observe(viewLifecycleOwner) { p ->
                adapter = ProductsRecyclerViewAdapter(p)
            }
        }

        binding.addProduct.setOnClickListener {
            val action = ProductsFragmentDirections.actionProductsFragmentToProductCreate()
            Navigation.findNavController(it).navigate(action)
        }

        return view
    }



    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProductsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}