package com.tmdbcodlab.android.ui.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.tmdbcodlab.android.R

/**
 * Created by ronelg on 12/19/17.
 */
class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        title = "Now Playing Movies"

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragContainer, MoviesFragment.newInstance(), MoviesFragment::class.java.simpleName)
                .commit()
    }
}