package com.tmdbcodlab.android.data

import com.tmdbcodlab.android.data.source.TmdbDataSource
import com.tmdbcodlab.android.data.source.local.TmdbLocalDataSource
import com.tmdbcodlab.android.data.source.remote.TmdbRemoteDataSource
import com.tmdbcodlab.android.io.Movie
import com.tmdbcodlab.android.io.MovieDetails
import com.tmdbcodlab.android.io.Trailer
import io.reactivex.Flowable

class TmdbRepository: TmdbDataSource {

    var local : TmdbDataSource = TmdbLocalDataSource()
    var remote : TmdbDataSource = TmdbRemoteDataSource()

    override fun getMovieDetails(movieId: Int): Flowable<MovieDetails> {
        return remote.getMovieDetails(movieId)
    }

    override fun getMovieTrailers(movieId: Int): Flowable<Trailer> {
        return remote.getMovieTrailers(movieId)
    }

    override fun getNowPlayingMovies(page: Int): Flowable<MutableList<Movie>> {
        return remote.getNowPlayingMovies(page)
    }

    override fun getPopularMovies(page: Int): Flowable<MutableList<Movie>> {
        return remote.getPopularMovies(page)
    }

    override fun getUpcomingMovies(page: Int): Flowable<MutableList<Movie>> {
        return remote.getUpcomingMovies(page)
    }

    override fun getTopRatedMovies(page: Int): Flowable<MutableList<Movie>> {
        return remote.getTopRatedMovies(page)
    }


    companion object {

        @Volatile private var sSingleton: TmdbRepository? = null

        val instance: TmdbRepository
            get() {
                if (sSingleton == null) {
                    synchronized(TmdbRepository::class.java) {
                        sSingleton = TmdbRepository()
                    }
                }
                return sSingleton!!
            }

    }
}
