package com.tmdbcodlab.android.ui.movies

import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.tmdbcodlab.android.data.TmdbRepository
import com.tmdbcodlab.android.io.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(fragView: MoviesContract.View) : MoviesContract.Presenter {

    private var mTmdbRepo: TmdbRepository
    private var mFragView: MoviesContract.View = fragView

    private var mMoviesDisposable: Disposable? = null
    private var mCompositeDisposable = CompositeDisposable()

    private var mType: Int = Movie.TYPE_NOW_PLAYING // default type
    private var mPage: Int = 1

    init {
        fragView.setPresenter(this)
        mTmdbRepo = TmdbRepository.instance
    }

    override fun subscribe() {
        d { "subscribe" }
        mFragView.setLoadingIndicator(true)
        loadMovies(Movie.TYPE_NOW_PLAYING, mPage, false)
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }


    override fun loadMovies(type: Int?, page: Int?, forceUpdate: Boolean) {
        mType = type ?: Movie.TYPE_NOW_PLAYING

        if (page == null) {
            mPage++
        }

        d { "loadMovies type: $mType page: $page forceUpdate: $forceUpdate" }

        when (mType) {
            Movie.TYPE_NOW_PLAYING -> {
                mMoviesDisposable = mTmdbRepo.getNowPlayingMovies(mPage)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnError { t -> e { "getNowPlayingMovies onError: $t" } }
                        .subscribe { t: List<Movie>? ->
                            d { "nowPlayingMovies onNext: ${t!!.size}" }
                            mFragView.onMoviesLoaded(t as MutableList<Movie>)
                            mFragView.setLoadingIndicator(false)
                        }
            }

            Movie.TYPE_TOP_RATED -> {
                mMoviesDisposable = mTmdbRepo.getTopRatedMovies(mPage)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnError { t -> e { "getTopRatedMovies onError: $t" } }
                        .subscribe { t: List<Movie>? ->
                            d { "topRatedMovies: ${t!!.size}" }
                            mFragView.onMoviesLoaded(t as MutableList<Movie>)
                            mFragView.setLoadingIndicator(false)
                        }
            }

            Movie.TYPE_POPULAR -> {
                mMoviesDisposable = mTmdbRepo.getPopularMovies(mPage)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnError { t -> e { "getPopularMovies onError: $t" } }
                        .subscribe { t: List<Movie>? ->
                            d { "popularMovies: ${t!!.size}" }
                            mFragView.onMoviesLoaded(t as MutableList<Movie>)
                            mFragView.setLoadingIndicator(false)
                        }
            }

            Movie.TYPE_UPCOMING -> {
                mMoviesDisposable = mTmdbRepo.getUpcomingMovies(mPage)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnError { t -> e { "getUpcomingMovies onError: $t" } }
                        .subscribe { t: List<Movie>? ->
                            d { "upcomingMovies ${t!!.size}" }
                            mFragView.onMoviesLoaded(t as MutableList<Movie>)
                            mFragView.setLoadingIndicator(false)
                        }
            }
        }
        mCompositeDisposable.add(mMoviesDisposable!!)
    }
}