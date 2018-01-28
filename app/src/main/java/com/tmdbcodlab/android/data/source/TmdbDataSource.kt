package com.tmdbcodlab.android.data.source

import com.tmdbcodlab.android.io.Movie
import com.tmdbcodlab.android.io.MovieDetails
import com.tmdbcodlab.android.io.MoviesResult
import com.tmdbcodlab.android.io.Trailer
import io.reactivex.Flowable

interface TmdbDataSource {

    fun getNowPlayingMovies(page: Int = 1): Flowable<MutableList<Movie>>

    fun getPopularMovies(page: Int = 1): Flowable<MutableList<Movie>>

    fun getUpcomingMovies(page: Int = 1): Flowable<MutableList<Movie>>

    fun getTopRatedMovies(page: Int = 1): Flowable<MutableList<Movie>>

    fun getMovieDetails(movieId: Int): Flowable<MovieDetails>

    fun getMovieTrailers(movieId: Int): Flowable<Trailer>
}