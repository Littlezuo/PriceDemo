package com.little.easymv.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.chad.library.adapter.base.BaseViewHolder
import com.jaydenxiao.common.baseadapter.MyBaseQuickAdapter
import com.jaydenxiao.common.commonutils.GlideRoundTransformUtil
import com.little.easymv.R
import com.little.easymv.extension.loadImage
import com.little.easymv.responsebean.ClassifyResponse
import kotlinx.android.synthetic.main.item_comic.view.*

/**
 * Created by Littlezuo on 2017/9/5.
 */
class ComicAdapter : MyBaseQuickAdapter<ClassifyResponse> {
    constructor(data: List<ClassifyResponse>?, recyclerView: RecyclerView?) : super(R.layout.item_comic, data, recyclerView) {

    }

    override fun convert(helper: BaseViewHolder?, itemView: View, item: ClassifyResponse) {
//        LogUtils.loge("helper $helper")
//        val imagView = helper?.getView<ImageView>(R.id.category_iv)
        loadImage(mContext, item.cover)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .transform(CenterCrop(mContext),GlideRoundTransformUtil(mContext,5))
                .into(itemView.category_iv)
//        ImageLoaderUtils.display(mContext,imagView,item.cover)
//        ImageLoaderUtils.displayRound(mContext,itemView.category_iv,item.cover)
    }

}

