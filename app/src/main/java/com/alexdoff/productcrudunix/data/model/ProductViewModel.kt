package com.alexdoff.productcrudunix.data.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexdoff.productcrudunix.data.obj.GetProductsResponse
import com.alexdoff.productcrudunix.data.obj.PostAndPutProductRequest
import com.alexdoff.productcrudunix.data.obj.Product
import com.alexdoff.productcrudunix.data.repo.ProductRepo
import kotlinx.coroutines.*

private const val RTAG = "ResponseError"

class ProductViewModel(private val repo: ProductRepo): ViewModel() {
    val products = MutableLiveData<List<Product>>()
    val success = MutableLiveData<Boolean>(false)
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

    fun createProduct(p: PostAndPutProductRequest) {
        job = CoroutineScope(Dispatchers.IO + ex).launch {
            val res = repo.createProduct(p)
            withContext(Dispatchers.Main) {
                if (res.isSuccessful) {
                    success.postValue(true)
                } else {
                    success.postValue(false)
                    onError(res.message().toString())
                }
            }
        }
    }

    fun deleteProduct(id: String) {
        job = CoroutineScope(Dispatchers.IO + ex).launch {
            val res = repo.deleteProduct(id)
            withContext(Dispatchers.Main) {
                if(res.isSuccessful) {
                    success.postValue(true)
                } else {
                    success.postValue(false)
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