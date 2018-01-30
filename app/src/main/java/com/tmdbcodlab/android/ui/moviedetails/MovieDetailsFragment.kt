package com.tmdbcodlab.android.ui.moviedetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.ajalt.timberkt.d
import com.tmdbcodlab.android.MovieUtils
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.api.TmdbService
import com.tmdbcodlab.android.io.Movie
import com.tmdbcodlab.android.io.MovieDetails
import com.tmdbcodlab.android.io.Trailer
import java.util.*

class MovieDetailsFragment : Fragment(), MovieDetailsContract.View {

    private lateinit var mMovie: Movie

    private lateinit var mPresenter: MovieDetailsContract.Presenter
    // Views
    private lateinit var tvReleaseDate: TextView

    private lateinit var ivPoster: ImageView
    private lateinit var tvDuration: TextView
    private lateinit var tvVoteAverage: TextView
    private lateinit var tvOverview: TextView
    private lateinit var tvTagline: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_movie_details, container, false)
        initViews(root)
        return root
    }

    override fun onResume() {
        super.onResume()
        mPresenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.unsubscribe()
    }

    private fun initViews(root: View) {
        tvReleaseDate = root.findViewById(R.id.tvReleaseDate)
        tvReleaseDate.text = String.format(Locale.getDefault(), "Release Date: %s", mMovie.release_date)

        ivPoster = root.findViewById(R.id.ivPoster)
        MovieUtils.loadMoviePoster(ivPoster, TmdbService.POSTER_URL + mMovie.poster_path)

        // need to pull from details request
        tvDuration = root.findViewById(R.id.tvDuration)
        tvTagline = root.findViewById(R.id.tvTagline)

        tvVoteAverage = root.findViewById(R.id.tvVoteAverage)
        tvVoteAverage.text = String.format(Locale.getDefault(), "Vote Average: %s/10", mMovie.vote_average)

        tvOverview = root.findViewById(R.id.tvOverview)
        tvOverview.text = mMovie.overview
    }

    override fun setSelectedMovie(movie: Movie) {
        mMovie = movie
    }

    override fun setPresenter(presenter: MovieDetailsContract.Presenter) {
        mPresenter = presenter
    }

    override fun updateMovieDetails(movieDetails: MovieDetails) {
        d { "updateMovieDetails $movieDetails" }
        tvDuration.text = String.format(Locale.getDefault(), "Duration: %s", movieDetails.runtime.toString())
        if (movieDetails.tagline.isNullOrBlank()) {
            tvTagline.visibility = View.GONE
        } else {
            tvTagline.text = movieDetails.tagline
        }
    }

    override fun updateMovieTrailers(trailer: Trailer) {
        d { "updateMovieTrailers $trailer" }

    }

    companion object {
        fun newInstance(/*args: Bundle*/): MovieDetailsFragment {
            val frag = MovieDetailsFragment()
//            frag.arguments = args
            return frag
        }
    }
}