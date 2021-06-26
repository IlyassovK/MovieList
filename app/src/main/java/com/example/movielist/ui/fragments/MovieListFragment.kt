package com.example.movielist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.R
import com.example.movielist.adapters.MovieListAdapter
import com.example.movielist.databinding.FragmentMovieListBinding
import com.example.movielist.other.Status
import com.example.movielist.ui.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val movieListViewModel: MovieListViewModel by viewModels()

    private lateinit var binding: FragmentMovieListBinding

    @Inject
    lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieListBinding.bind(view)
        setupRecyclerView()
        subscribeObservers()

    }

    private fun subscribeObservers() {
        movieListViewModel.movieList.observe(viewLifecycleOwner){ result ->
            when(result.status){
                Status.SUCCESS -> {
                    binding.rvMovieList.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    movieListAdapter.movieList = result.data!!.results
                }
                Status.LOADING -> {
                    binding.rvMovieList.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(context, "Unknown error occurred", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun setupRecyclerView(){
        rvMovieList.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}