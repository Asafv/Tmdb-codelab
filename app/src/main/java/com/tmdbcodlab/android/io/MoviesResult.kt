package com.tmdbcodlab.android.io

data class MoviesResult(var page: Int, var total_results: Int, var total_pages: Int, var results: List<Movie>)