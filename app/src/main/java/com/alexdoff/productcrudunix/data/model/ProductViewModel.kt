package com.alexdoff.productcrudunix.data.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexdoff.productcrudunix.data.obj.GetProductsResponse
import com.alexdoff.productcrudunix.data.obj.Product
import com.alexdoff.productcrudunix.data.repo.ProductRepo
import kotlinx.coroutines.*

private const val RTAG = "ResponseError"

class ProductViewModel(private val repo: ProductRepo): ViewModel() {
    val products = MutableLiveData<List<Product>>()
    private val ex = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    private var job: Job? = null

    fun getAllProduct() {
        job = CoroutineScope(Dispatchers.IO + ex).launch {
            val res = repo.getAllProduct()
            withContext(Dispatchers.Main) {
                if(res.isSuccessful) {
                    products.postValue(res.body()?.products)
                } else {
                    onError(res.message().toString())
                }
            }
        }
    }

    fun cancelRequest() {
        job?.cancel()
    }

    private fun onError(msg: String) {
        Log.e(RTAG, msg)
        job?.cancel()
    }
}