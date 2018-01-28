package com.tmdbcodlab.android.ui.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.tmdbcodlab.android.R

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        title = "Now Playing Movies"

        val moviesFrag = MoviesFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, moviesFrag, MoviesFragment::class.java.simpleName)
                .commit()

        // create the presenter
        MoviesPresenter(moviesFrag)
    }
}