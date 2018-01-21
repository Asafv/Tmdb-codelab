package com.tmdbcodlab.android.io

import java.io.Serializable

/**
 * Created by ronelg on 12/19/17.
 */
data class Movie(var id: Int, var title: String, var vote_average: Number, var popularity: Number,
                 var poster_path: String, var original_language: String, var original_title: String,
                 var backdrop_path: String, var adult: Boolean, var overview: String,
                 var release_date: String, var vote_count: Int, var genre_ids: List<Int>) : Serializable {

    companion object {
        const val TYPE_NOW_PLAYING = 1
        const val TYPE_TOP_RATED = 2
        const val TYPE_UPCOMING = 3
        const val TYPE_POPULAR = 4
    }
}