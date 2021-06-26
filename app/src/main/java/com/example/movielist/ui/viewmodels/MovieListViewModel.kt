package com.example.movielist.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movielist.data.model.Movie
import com.example.movielist.other.Resource
import com.example.movielist.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel(){

    val movieList: LiveData<Resource<Movie>> = repository.movieList

    init {
        getMovieList()
    }

    fun getMovieList(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getMovies()
        }
    }

}