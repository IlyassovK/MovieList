package com.example.movielist.data.network

import com.example.movielist.other.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private var retrofit: Retrofit? = null
        val retrofitInstance: Retrofit?
            get() {
                if(retrofit == null){
                    retrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }
    }
}