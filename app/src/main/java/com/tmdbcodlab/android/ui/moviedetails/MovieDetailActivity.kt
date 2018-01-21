package com.tmdbcodlab.android.ui.moviedetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tmdbcodlab.android.R

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, MovieDetailFragment.newInstance(intent.extras),
                        MovieDetailFragment::class.java.simpleName)
                .commit()
    }
}