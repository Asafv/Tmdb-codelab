package com.tmdbcodlab.android.ui.moviedetails

import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.tmdbcodlab.android.data.TmdbRepository
import com.tmdbcodlab.android.io.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsPresenter(private val mView: MovieDetailsContract.View,
                            private val mMovie: Movie,
                            private val mTmdbRepo: TmdbRepository)
    : MovieDetailsContract.Presenter {

    private var mCompositeDisposable = CompositeDisposable()

    init {
        mView.setPresenter(this)
        mView.setSelectedMovie(mMovie)
    }

    override fun subscribe() {
        // get movie details
        val detailsDisposable = mTmdbRepo.getMovieDetails(mMovie.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError { e { "getMovieDetails ERR: $it" } }
                .subscribe {
                    d { "getMovieDetails onNext: $it" }
                    mView.updateMovieDetails(it)
                }

        val trailersDisposable = mTmdbRepo.getMovieTrailers(mMovie.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError { e { "TgetMovieTrailers, ERR:  $it" } }
                .subscribe {
                    d { "getMovieTrailers onNext: $it" }
                    mView.updateMovieTrailers(it)
                }

        mCompositeDisposable.add(detailsDisposable!!)
        mCompositeDisposable.add(trailersDisposable!!)
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }
}