package com.example.movielist.data.network

import com.example.movielist.data.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataServices {
    @GET("reviews/all.json")
    fun getMovieList(@Query("api-key") apiKey: String): Call<Movie>
}