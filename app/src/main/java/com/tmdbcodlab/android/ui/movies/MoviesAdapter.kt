package com.tmdbcodlab.android.ui.movies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.github.ajalt.timberkt.d
import com.tmdbcodlab.android.MovieUtils
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.api.TmdbService
import com.tmdbcodlab.android.io.Movie

/**
 * Created by AsafV on 21/12/2017.
 */
class MoviesAdapter(private val movies: MutableList<Movie>)
    : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private lateinit var moviesAdapterListener: MoviesAdapterListener

    interface MoviesAdapterListener {
        fun onBottomReached()

        fun onMovieClicked(movie: Movie)
    }

    fun setMoviesAdapterListener(listener: MoviesAdapterListener) {
        moviesAdapterListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent!!.context)
                .inflate(R.layout.movie_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])

        if (position == itemCount - 1) {
            moviesAdapterListener.onBottomReached()
        }

        holder.itemView.setOnClickListener({ moviesAdapterListener.onMovieClicked(movies[position]) })

        d { "itemCount: $itemCount" }
        d { "position: $position" }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movies: List<Movie>) {
        val lastItem = itemCount - 1
        this.movies.addAll(movies)
        notifyItemRangeChanged(lastItem, itemCount)
    }

    fun removeAll() {
        movies.clear()
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        private var ivPoster: ImageView = itemView!!.findViewById(R.id.ivPoster)

        fun bind(movie: Movie) {
            MovieUtils.loadMoviePoster(ivPoster, TmdbService.POSTER_URL + movie.poster_path)
        }
    }
}

