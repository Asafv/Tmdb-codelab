package com.tmdbcodlab.android.api

import com.tmdbcodlab.android.api.TmdbService.Companion.NOW_PLAYING
import com.tmdbcodlab.android.io.MoviesResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by ronelg on 12/19/17.
 */
interface TmdbService {

    @GET(NOW_PLAYING)
    fun getNowPlayingMovies(
            @Query("page") page: Int
    ) : Flowable<   MoviesResult>

    @GET(POPULAR)
    fun getPopularMovies(
            @Query("page") page: Int
    ) : Flowable<MoviesResult>

    @GET(TOP_RATED)
    fun getTopRatedMovies(
            @Query("page") page: Int
    ) : Flowable<MoviesResult>

    @GET(UPCOMING)
    fun getUpcomingMovies(
            @Query("page") page: Int
    ) : Flowable<MoviesResult>


    companion object {

        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val POSTER_URL = "http://image.tmdb.org/t/p/w185/"
        const val API_KEY = "b331218ddcbd128634135abf7673fab5"

        const val NOW_PLAYING = "now_playing"
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
    }
}