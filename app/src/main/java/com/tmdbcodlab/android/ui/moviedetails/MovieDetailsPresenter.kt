package com.tmdbcodlab.android.ui.moviedetails

import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.tmdbcodlab.android.data.TmdbRepository
import com.tmdbcodlab.android.io.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsPresenter(private val mView: MovieDetailsContract.View, private val mMovie: Movie)
    : MovieDetailsContract.Presenter {

    private var mTmdbRepo: TmdbRepository
    private var mCompositeDisposable = CompositeDisposable()
    private var mDetailsDisposable: Disposable? = null
    private var mTrailersDisposable: Disposable? = null

    init {
        mView.setPresenter(this)
        mTmdbRepo = TmdbRepository.instance
    }

    override fun subscribe() {
        // get movie details
        mDetailsDisposable = mTmdbRepo.getMovieDetails(mMovie.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError { run { "getMovieDetails ERR: $it" } }
                .subscribe {
                    d { "getMovieDetails onNext: $it" }
                    mView.updateMovieDetails(it)
                }

        mTrailersDisposable = mTmdbRepo.getMovieTrailers(mMovie.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError { e { "TgetMovieTrailers, ERR:  $it" } }
                .subscribe {
                    d { "getMovieTrailers onNext: $it" }
                    mView.updateMovieTrailers(it)
                }

        mCompositeDisposable.add(mDetailsDisposable!!)
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }
}