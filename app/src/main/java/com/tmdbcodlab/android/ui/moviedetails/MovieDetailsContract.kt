package com.tmdbcodlab.android.ui.moviedetails

import com.tikalk.mobileevent.mobileevent.BasePresenter
import com.tikalk.mobileevent.mobileevent.BaseView
import com.tmdbcodlab.android.io.Movie
import com.tmdbcodlab.android.io.MovieDetails
import com.tmdbcodlab.android.io.Trailer

interface MovieDetailsContract {

    interface View : BaseView<Presenter> {
        fun updateMovieDetails(movieDetails: MovieDetails)
        fun updateMovieTrailers(trailer: Trailer)
        fun setSelectedMovie(movie: Movie)
    }

    interface Presenter : BasePresenter {

    }
}