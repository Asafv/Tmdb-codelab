package com.tmdbcodlab.android.data.source

import com.tmdbcodlab.android.io.Movie
import com.tmdbcodlab.android.io.MoviesResult
import io.reactivex.Flowable

/**
 * Created by ronelg on 12/19/17.
 */
interface TmdbDataSource {

    fun getNowPlayingMovies(page: Int = 1): Flowable<MutableList<Movie>>

    fun getPopularMovies(page: Int = 1): Flowable<MutableList<Movie>>

    fun getUpcomingMovies(page: Int = 1): Flowable<MutableList<Movie>>

    fun getTopRatedMovies(page: Int = 1): Flowable<MutableList<Movie>>

//    val popularMovies: Flowable<MutableList<Movie>>?

//    val upcomingMovies: Flowable<MutableList<Movie>>?

//    val topRatedMovies: Flowable<MutableList<Movie>>?
}