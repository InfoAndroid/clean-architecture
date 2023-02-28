package com.mukesh.cleanarchitectureexm.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mukesh.cleanarchitectureexm.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("urlToImage")
fun urlToImage(view:ImageView,s:String?){
    s.let {
        val options = RequestOptions.placeholderOf(R.drawable.loading).error(R.drawable.error)
        Glide.with(view).setDefaultRequestOptions(options).load(s?:"").into(view)
    }

}