package com.tmdbcodlab.android.data.source.local

import com.tmdbcodlab.android.data.source.TmdbDataSource
import com.tmdbcodlab.android.io.Movie
import io.reactivex.Flowable

/**
 * Created by ronelg on 12/19/17.
 */
class TmdbLocalDataSource: TmdbDataSource {
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