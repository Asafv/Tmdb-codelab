package com.tmdbcodlab.android.ui.moviedetails

import com.tikalk.mobileevent.mobileevent.BasePresenter
import com.tikalk.mobileevent.mobileevent.BaseView
import com.tmdbcodlab.android.io.MovieDetails
import com.tmdbcodlab.android.io.Trailer

interface MovieDetailsContract {

    interface View : BaseView<Presenter> {
        fun updateMovieDetails(movieDetails: MovieDetails)
        fun updateMovieTrailers(it: Trailer)
    }

    interface Presenter : BasePresenter {

    }
}