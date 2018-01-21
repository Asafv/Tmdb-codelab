package com.tmdbcodlab.android.ui.moviedetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tmdbcodlab.android.MovieUtils
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.api.TmdbService
import com.tmdbcodlab.android.io.Movie
import java.util.*

class MovieDetailFragment : Fragment(), MovieDetailsContract.View {

    private lateinit var mMovie: Movie

    // Views
    private lateinit var tvReleaseDate: TextView
    private lateinit var ivPoster: ImageView
    private lateinit var tvDuration: TextView
    private lateinit var tvVoteAverage: TextView
    private lateinit var tvOverview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            // get details args
            mMovie = arguments!!.get("movie") as Movie
        }

        activity!!.title = mMovie.title
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_movie_details, container, false)

        initViews(root)

        return root
    }

    private fun initViews(root: View) {
        tvReleaseDate = root.findViewById(R.id.tvReleaseDate)
        tvReleaseDate.text = mMovie.release_date

        ivPoster = root.findViewById(R.id.ivPoster)
        MovieUtils.loadMoviePoster(ivPoster, TmdbService.POSTER_URL + mMovie.poster_path)

        // need to pull from details request
        tvDuration = root.findViewById(R.id.tvDuration)

        tvVoteAverage = root.findViewById(R.id.tvVoteAverage)
        tvVoteAverage.text = String.format(Locale.getDefault(), "%s/10", mMovie.vote_average)

        tvOverview = root.findViewById(R.id.tvOverview)
        tvOverview.text = mMovie.overview

    }

    override fun setPresenter(presenter: MovieDetailsContract.Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance(args: Bundle): MovieDetailFragment {
            val frag = MovieDetailFragment()
            frag.arguments = args
            return frag
        }
    }
}