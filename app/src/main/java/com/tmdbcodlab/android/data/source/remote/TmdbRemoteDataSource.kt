package com.tmdbcodlab.android.data.source.remote

import com.github.ajalt.timberkt.d
import com.tmdbcodlab.android.api.TmdbApi
import com.tmdbcodlab.android.api.TmdbService
import com.tmdbcodlab.android.data.source.TmdbDataSource
import com.tmdbcodlab.android.io.Movie
import io.reactivex.Flowable

/**
 * Created by ronelg on 12/19/17.
 */
class TmdbRemoteDataSource : TmdbDataSource {
    private val mTmdbService: TmdbService = TmdbApi.instance.tmdbService!!

    override fun getNowPlayingMovies(page: Int): Flowable<MutableList<Movie>> {
        d { "getting nowPlayingMovies..." }
        return mTmdbService.getNowPlayingMovies(page)
                .flatMap { return@flatMap Flowable.fromArray(it.results.toMutableList()) }
    }

    override fun getPopularMovies(page: Int): Flowable<MutableList<Movie>> {
        d { "getting popularMovies..." }
        return mTmdbService.getPopularMovies(page)
                .flatMap { return@flatMap Flowable.fromArray(it.results.toMutableList()) }
    }

    override fun getUpcomingMovies(page: Int): Flowable<MutableList<Movie>> {
        d { "getting upcomingMovies..." }
        return mTmdbService.getUpcomingMovies(page)
                .flatMap { return@flatMap Flowable.fromArray(it.results.toMutableList()) }
    }

    override fun getTopRatedMovies(page: Int): Flowable<MutableList<Movie>> {
        d { "getting topRatedMovies..." }
        return mTmdbService.getTopRatedMovies(page)
                .flatMap { return@flatMap Flowable.fromArray(it.results.toMutableList()) }
    }

    // TODO This doesn't work Ronel please explain why
//    override val nowPlayingMovies: Flowable<MutableList<Movie>>
//        get() {
//            d { "getting nowPlayingMovies..." }
//            return mTmdbService.getNowPlayingMovies(page)
//                    .flatMap { return@flatMap field }
//        }

//    override val popularMovies: Flowable<MutableList<Movie>>? = null
//        get() {
//            d { "getting popularMovies..." }
//            return mTmdbService.getPopularMovies(page)
//                    .flatMap { return@flatMap field }
//        }

//    override val upcomingMovies: Flowable<MutableList<Movie>>? = null
//        get() {
//            d { "getting upcomingMovies..." }
//            return mTmdbService.getUpcomingMovies(page)
//                    .flatMap { return@flatMap field }
//        }

//    override val topRatedMovies: Flowable<MutableList<Movie>>? = null
//        get() {
//            d { "getting topRatedMovies..." }
//            return mTmdbService.getTopRatedMovies(page)
//                    .flatMap { return@flatMap field }
//        }
}