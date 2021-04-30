/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotlincoroutine.sample1

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

val shimmer = Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
    .setDuration(1000) // how long the shimmering animation takes to do one full sweep
    .setBaseAlpha(0.7f) //the alpha of the underlying children
    .setHighlightAlpha(0.6f) // the shimmer alpha amount
    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
    .setAutoStart(true)
    .build()

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
//        Glide.with(view.context)
//            .load(imageUrl)
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .dontAnimate()
//            .into(view)



// This is the placeholder for the imageView
        val d = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }

        Picasso.get()
            .load(imageUrl)
            .placeholder(d )
            .into(view)

    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}

@BindingAdapter("stopShimmering")
fun bindstopShimmering(
    view: ShimmerFrameLayout
    , isGone: Boolean?
) {
    if (isGone == null || isGone) {
        view.stopShimmer()
    } else {
        view.showShimmer(true)
    }
}