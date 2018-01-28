package com.tmdbcodlab.android.io

class MovieDetails(private val id: Int, private val title: String, private val overview: String?,
                   private val revenue: Int, private val popularity: Int, private val runtime: Int?,
                   private val backdrop_path: String?, private val tagline: String?, private val homepage: String?,
                   private val vote_average: Int, private val vote_count: Int)