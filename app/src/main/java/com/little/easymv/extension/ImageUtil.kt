package com.little.easymv.extension

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.DrawableTypeRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.jaydenxiao.common.commonutils.GlideCircleTransfromUtil
import com.little.easymv.R
import com.little.easymv.constants.UrlConstants

/**
 * Created by Littlezuo on 2017/9/1.
 */
fun loadImage(context: Context, imageView: ImageView?, url: String?) {
    if (imageView == null){
        return
    }
    var urls = ""
    if (url != null) urls = url!!
    loadImage(context, urls)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .placeholder(R.drawable.ic_image_loading)
            .error(R.drawable.ic_empty_picture)
            .crossFade()
            .into(imageView)
}

fun loadCircleImage(context: Context, imageView: ImageView, url: String) {
//    Glide.with(context).load(resId)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .error(com.jaydenxiao.common.R.drawable.toux2)
//            .centerCrop()
//            .transform(GlideCircleTransfromUtil(context))
//            .into(object : SimpleTarget<GlideDrawable>() {
//                override fun onResourceReady(resource: GlideDrawable, glideAnimation: GlideAnimation<in GlideDrawable>) {
//                    imageView.setImageDrawable(resource)
//                }
//            })
    loadImage(context, url).diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.article_default_image)
            .placeholder(R.drawable.article_default_image)
            .centerCrop()
            .transform(GlideCircleTransfromUtil(context))
            .into(imageView)
}

fun loadImage(imageView: ImageView?, url: String) {
    if (imageView == null){
        return
    }
    loadImage(imageView!!.context, url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .placeholder(R.drawable.ic_image_loading)
            .error(R.drawable.ic_empty_picture)
            .crossFade()
            .into(imageView)
}

fun loadImage(mContext: Context, url: String): DrawableTypeRequest<GlideUrl> {
    var glideUrl = GlideUrl(url, LazyHeaders.Builder()
            .addHeader("Referer", UrlConstants.COMMON_UA_STR)
            .build())
    val rawglide = Glide.with(mContext).load(glideUrl)
    return rawglide;
}
