package com.alexdoff.productcrudunix.ui.product.edit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.alexdoff.productcrudunix.R
import com.alexdoff.productcrudunix.data.model.ProductViewModel
import com.alexdoff.productcrudunix.data.obj.PostAndPutProductRequest
import com.alexdoff.productcrudunix.databinding.FragmentProductEditBinding
import com.alexdoff.productcrudunix.ui.product.get.ProductDetailArgs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "ProductEdit"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductEdit.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductEdit : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentProductEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var productViewModel: ProductViewModel
    private val args: ProductEditArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductEditBinding.inflate(inflater, container, false)
        val view = binding.root

        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]

        binding.editName.setText(args.product.name)
        binding.editPrice.setText(args.product.price)
        binding.editDesc.setText(args.product.description)

        binding.updateProduct.setOnClickListener {
            val pp = PostAndPutProductRequest(
                binding.editDesc.text.toString(),
                binding.editName.text.toString(),
                binding.editPrice.text.toString().toDouble()
            )
            productViewModel.updateProduct(args.product.pid!!, pp)

            // TODO: Reliable way to check network fail
            val action = ProductEditDirections.actionProductEditToProductsFragment()
            Navigation.findNavController(it).navigate(action)
//            if(productViewModel.success.value == true) {
//                productViewModel.success.value = false
//                val action = ProductEditDirections.actionProductEditToProductsFragment()
//                Navigation.findNavController(it).navigate(action)
//            }
        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductEdit.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductEdit().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}