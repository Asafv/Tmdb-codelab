package com.tmdbcodlab.android.ui.movies

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

/**
 * Created by AsafV on 21/12/2017.
 */
class MoviesFragment : Fragment(), MoviesContract.View, MoviesAdapter.MoviesAdapterListener {

    // Members
    private lateinit var mPresenter: MoviesPresenter
    private var mMoviesAdapter: MoviesAdapter? = null

    // Views
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var rvMovies: RecyclerView
    private lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mPresenter = MoviesPresenter(this)
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



    private fun initRecyclerView() {
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_movies, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_now_playing -> {
                d { "menu_now_playing" }
                mMoviesAdapter = null
                mPresenter.loadMovies(Movie.TYPE_NOW_PLAYING, page = 1, forceUpdate = true)
                return true
            }

            R.id.menu_popular -> {
                d { "menu_popular" }
                mMoviesAdapter = null
                mPresenter.loadMovies(Movie.TYPE_POPULAR, page = 1, forceUpdate = true)
                return true
            }

            R.id.menu_top_rated -> {
                d { "menu_top_rated" }
                mMoviesAdapter = null
                mPresenter.loadMovies(Movie.TYPE_TOP_RATED, page = 1, forceUpdate = true)
                return true
            }

            R.id.menu_upcoming -> {
                d { "menu_upcoming" }
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

    override fun setPresenter(presenter: MoviesContract.Presenter) {
        mPresenter = presenter as MoviesPresenter
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

    companion object {
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}