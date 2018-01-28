package com.tmdbcodlab.android.data.source.local

import com.tmdbcodlab.android.data.source.TmdbDataSource
import com.tmdbcodlab.android.io.Movie
import com.tmdbcodlab.android.io.MovieDetails
import com.tmdbcodlab.android.io.Trailer
import io.reactivex.Flowable

class TmdbLocalDataSource: TmdbDataSource {

    override fun getMovieDetails(movieId: Int): Flowable<MovieDetails> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovieTrailers(movieId: Int): Flowable<Trailer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNowPlayingMovies(page: Int): Flowable<MutableList<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPopularMovies(page: Int): Flowable<MutableList<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpcomingMovies(page: Int): Flowable<MutableList<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTopRatedMovies(page: Int): Flowable<MutableList<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}