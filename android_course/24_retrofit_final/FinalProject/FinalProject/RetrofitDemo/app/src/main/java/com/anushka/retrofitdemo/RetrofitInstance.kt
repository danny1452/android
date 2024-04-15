package com.anushka.retrofitdemo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    // singleton instance of retrofit
    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"

        // to display okhttp logs
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        // to configure okhttp
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor) // interceptor for client
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
        }.build()

        // create instance using builder
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)// to set base url
                .client(client)// to set okhttp client
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    // converter to convert json to java
                .build()
        }

    }
}