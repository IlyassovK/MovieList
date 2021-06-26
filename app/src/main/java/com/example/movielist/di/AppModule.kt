package com.example.movielist.di

import android.content.Context
import com.example.movielist.adapters.MovieListAdapter
import com.example.movielist.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMainRepository(@ApplicationContext context: Context) = MainRepository(context)

    @Singleton
    @Provides
    fun provideMovieListAdapter() = MovieListAdapter()
}