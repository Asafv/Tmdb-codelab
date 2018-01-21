package com.tmdbcodlab.android

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Created by AsafV on 21/01/2018.
 */

class MovieUtils {
    companion object {
        fun loadMoviePoster(imageView: ImageView, url: String) {
            Glide.with(imageView)
                    .load(url)
                    .apply(RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(R.drawable.movie_poster_placeholder)
                            .error(RequestOptions().errorPlaceholder)
                            .fitCenter())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
        }
    }
}
