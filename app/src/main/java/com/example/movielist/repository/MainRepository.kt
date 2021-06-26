package com.example.movielist.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movielist.data.model.Movie
import com.example.movielist.data.network.GetDataServices
import com.example.movielist.data.network.RetrofitInstance
import com.example.movielist.other.Constants
import com.example.movielist.other.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import javax.inject.Inject

class MainRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val _movieList = MutableLiveData<Resource<Movie>>()
    val movieList: LiveData<Resource<Movie>> = _movieList

    init {
        _movieList.postValue(Resource.loading(null))
    }


    suspend fun getMovies(){
        val service = RetrofitInstance.retrofitInstance!!.create(GetDataServices::class.java)
        val call = service.getMovieList(Constants.API_KEY)

        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                _movieList.postValue(Resource.success(response.body()))
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                _movieList.postValue(Resource.error("Can't catch data", null))
            }

        })
    }
}