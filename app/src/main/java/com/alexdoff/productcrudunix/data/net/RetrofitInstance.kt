package com.alexdoff.productcrudunix.data.net

import com.alexdoff.productcrudunix.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private const val url = "https://procrud.my.id/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private fun interceptor(): HttpLoggingInterceptor {
        val log = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG) {
            log.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            log.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return log
    }
    private val client = OkHttpClient
        .Builder()
        .addInterceptor(interceptor())
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val api: ProductApiInterface by lazy {
        retrofit.create(ProductApiInterface::class.java)
    }
}