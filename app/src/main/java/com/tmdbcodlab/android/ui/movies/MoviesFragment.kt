package com.tmdbcodlab.android.ui.movies

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.github.ajalt.timberkt.d
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.io.Movie
import com.tmdbcodlab.android.ui.moviedetails.MovieDetailsActivity

/**
 * Created by AsafV on 21/12/2017.
 */
class MoviesFragment : Fragment(), MoviesContract.View, MoviesAdapter.MoviesAdapterListener {

    private lateinit var mPresenter: MoviesContract.Presenter
    private var mMoviesAdapter: MoviesAdapter? = null
    // Views
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var rvMovies: RecyclerView
    private lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_movies, container, false)
        d { "onCreateView" }

        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout)
        rvMovies = root.findViewById(R.id.rvMoviesList)
        tvMessage = root.findViewById(R.id.tvMessage)

        initRecyclerView()

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_movies, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_now_playing -> {
                d { "menu_now_playing" }
                activity!!.title = "Now Playing Movies"
                mMoviesAdapter = null
                mPresenter.loadMovies(Movie.TYPE_NOW_PLAYING, page = 1, forceUpdate = true)
                return true
            }

            R.id.menu_popular -> {
                d { "menu_popular" }
                activity!!.title = "Popular Movies"
                mMoviesAdapter = null
                mPresenter.loadMovies(Movie.TYPE_POPULAR, page = 1, forceUpdate = true)
                return true
            }

            R.id.menu_top_rated -> {
                d { "menu_top_rated" }
                activity!!.title = "Top Rated Movies"
                mMoviesAdapter = null
                mPresenter.loadMovies(Movie.TYPE_TOP_RATED, page = 1, forceUpdate = true)
                return true
            }

            R.id.menu_upcoming -> {
                d { "menu_upcoming" }
                activity!!.title = "Upcoming Movies"
                mMoviesAdapter = null
                mPresenter.loadMovies(Movie.TYPE_UPCOMING, page = 1, forceUpdate = true)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.unsubscribe()
    }

    override fun onBottomReached() {
        d { "onBottomReached" }
        mPresenter.loadMovies(null, null, false)
    }

    override fun onMovieClicked(movie: Movie) {
        d { "onMovieClicked: $movie" }
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    override fun onMoviesLoaded(movies: MutableList<Movie>) {
        d { "onMoviesLoaded" }
        if (mMoviesAdapter == null) {
            mMoviesAdapter = MoviesAdapter(movies)
            rvMovies.adapter = mMoviesAdapter
            mMoviesAdapter!!.setMoviesAdapterListener(this)
        } else {
            mMoviesAdapter!!.addMovies(movies)
        }
    }

    override fun onMoreMoviesLoaded(movies: List<Movie>) {
        mMoviesAdapter!!.addMovies(movies)
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            tvMessage.visibility = View.VISIBLE
            tvMessage.text = message
        } else {
            tvMessage.visibility = View.GONE
        }
    }

    override fun setLoadingIndicator(active: Boolean) {
        d { "setLoadingIndicator: $active" }
        swipeRefreshLayout.isRefreshing = active
    }

    override fun setPresenter(presenter: MoviesContract.Presenter) {
        mPresenter = presenter
    }

    private fun initRecyclerView() {
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = GridLayoutManager(context, 2)
    }

    companion object {
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}