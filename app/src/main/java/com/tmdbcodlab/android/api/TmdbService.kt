package com.tmdbcodlab.android.api

import com.tmdbcodlab.android.io.MovieDetails
import com.tmdbcodlab.android.io.MoviesResult
import com.tmdbcodlab.android.io.Trailer
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbService {

    @GET(NOW_PLAYING)
    fun getNowPlayingMovies(
            @Query("page") page: Int
    ): Flowable<MoviesResult>

    @GET(POPULAR)
    fun getPopularMovies(
            @Query("page") page: Int
    ): Flowable<MoviesResult>

    @GET(TOP_RATED)
    fun getTopRatedMovies(
            @Query("page") page: Int
    ): Flowable<MoviesResult>

    @GET(UPCOMING)
    fun getUpcomingMovies(
            @Query("page") page: Int
    ): Flowable<MoviesResult>

    @GET(MOVIE_DETAILS)
    fun getMovieDetails(
            @Path("movie_id") movieId: Int
    ): Flowable<MovieDetails>

    @GET(MOVIE_TRAILERS)
    fun getMovieTrailers(
            @Path("movie_id") movieId: Int
    ): Flowable<Trailer>


    companion object {

        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val POSTER_URL = "http://image.tmdb.org/t/p/w185/"
        const val API_KEY = "b331218ddcbd128634135abf7673fab5"

        const val MOVIE_DETAILS = "movie/{movie_id}"
        const val MOVIE_TRAILERS = "movie/{movie_id}/videos"
        const val NOW_PLAYING = "now_playing"
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
    }
}