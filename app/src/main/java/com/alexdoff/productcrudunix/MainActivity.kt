package com.alexdoff.productcrudunix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.alexdoff.productcrudunix.data.model.ProductViewModel
import com.alexdoff.productcrudunix.data.model.ProductViewModelFactory
import com.alexdoff.productcrudunix.data.net.RetrofitInstance
import com.alexdoff.productcrudunix.data.repo.ProductRepo
import kotlinx.coroutines.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = RetrofitInstance.api
        val productRepo = ProductRepo(api)
        productViewModel = ViewModelProvider(
            this,
            ProductViewModelFactory(productRepo))[ProductViewModel::class.java]

    }
}