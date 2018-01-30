package com.tmdbcodlab.android.ui.moviedetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tmdbcodlab.android.R
import com.tmdbcodlab.android.data.TmdbRepository
import com.tmdbcodlab.android.io.Movie

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        val movie = intent.extras.get("movie") as Movie
        title = movie.title

        val movieDetailFragment = MovieDetailsFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, movieDetailFragment,
                        MovieDetailsFragment::class.java.simpleName)
                .commit()

            MovieDetailsPresenter(movieDetailFragment, movie, TmdbRepository.instance)
    }
}