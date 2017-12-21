package com.tmdbcodlab.android.data

import com.tmdbcodlab.android.data.source.TmdbDataSource
import com.tmdbcodlab.android.data.source.local.TmdbLocalDataSource
import com.tmdbcodlab.android.data.source.remote.TmdbRemoteDataSource
import com.tmdbcodlab.android.io.Movie
import io.reactivex.Flowable

/**
 * Created by ronelg on 12/19/17.
 */
class TmdbRepository: TmdbDataSource {

    var local : TmdbDataSource = TmdbLocalDataSource()
    var remote : TmdbDataSource = TmdbRemoteDataSource()

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


//    override val popularMovies: Flowable<MutableList<Movie>>?
//        get() {
//            // TODO add local check here
//            return remote.popularMovies
//        }

//    override val upcomingMovies: Flowable<MutableList<Movie>>?
//        get() {
//            // TODO add local check here
//            return remote.upcomingMovies
//        }

//    override val topRatedMovies: Flowable<MutableList<Movie>>?
//        get() {
//            // TODO add local check here
//            return remote.topRatedMovies
//        }

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
