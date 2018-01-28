package com.tmdbcodlab.android.ui.movies

import com.tikalk.mobileevent.mobileevent.BasePresenter
import com.tikalk.mobileevent.mobileevent.BaseView
import com.tmdbcodlab.android.io.Movie

interface MoviesContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(active: Boolean)

        fun showMessage(message: String?)

        fun onMoviesLoaded(movies: MutableList<Movie>)

        fun onMoreMoviesLoaded(movies: List<Movie>)
    }

    interface Presenter : BasePresenter {

        fun loadMovies(type: Int?, page: Int? = 1, forceUpdate: Boolean)
    }
}