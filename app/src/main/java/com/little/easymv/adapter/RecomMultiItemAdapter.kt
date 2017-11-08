package com.little.easymv.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jaydenxiao.common.baseadapter.MyMultiItemAdapter
import com.little.easymv.R
import com.little.easymv.extension.loadImage
import com.little.easymv.responsebean.RecommendResponse


/**
 * Created by Littlezuo on 2017/8/31.
 */

class RecomMultiItemAdapter : MyMultiItemAdapter<FormatRecomBean> {
    constructor(data: List<FormatRecomBean>?, recyclerView: RecyclerView) : super(data, recyclerView) {
//        LogUtils.loge("constructor....")
        addItemType(FormatRecomBean.BANNER, R.layout.layout_recom_banner)
        addItemType(FormatRecomBean.SIMALL_IMG, R.layout.layout_recom_small_img)
        addItemType(FormatRecomBean.BIG_IMG, R.layout.layout_recom_big_img)
        addItemType(FormatRecomBean.TITLE, R.layout.layout_recom_title)
        setSapn()
    }
    init {
//        LogUtils.loge("init....")
    }

    override fun convert(helper: BaseViewHolder, itemView: View, item: FormatRecomBean) {
        when (helper.itemViewType) {
            FormatRecomBean.BANNER -> setBanner(helper, item)
            FormatRecomBean.TITLE -> setTitle(helper, item)
            FormatRecomBean.SIMALL_IMG -> setImg(helper, item)
            FormatRecomBean.BIG_IMG -> setImg(helper, item)
        }
    }

    private fun setImg(helper: BaseViewHolder, item: FormatRecomBean) {
        val image = helper.getView<ImageView>(R.id.img)
//        loadImage(mContext, image, item.childitem?.cover)
        if (item.childitem?.cover != null)
            loadImage(mContext, item.childitem?.cover!!)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.pig)
                    .placeholder(R.drawable.ic_image_loading) //设置plcaceholder imagview的size必须固定,不然会显示异常
                    .error(R.drawable.ic_empty_picture)
//                .error(R.drawable.pig)
//                .centerCrop()
//                .dontAnimate()
                    .into(image)
        helper.setText(R.id.main_title, item.childitem?.title)
        helper.setVisible(R.id.sub_title, item.hasSubTitle)
        if (item.hasSubTitle && item.childitem?.sub_title != null && item.childitem?.sub_title!!.isNotEmpty()) helper.setText(R.id.sub_title, item.childitem?.sub_title)
    }

    private fun setTitle(helper: BaseViewHolder, item: FormatRecomBean) {
        helper.setText(R.id.title, item.title)
    }

    //    var tips = mutableListOf<String>()
    private fun setBanner(helper: BaseViewHolder, item: FormatRecomBean) {
        val bgaBanner = helper.getView<BGABanner>(R.id.banner)
        bgaBanner.setAdapter(BGABanner.Adapter<ImageView, RecommendResponse.DataBean> { banner, itemView, model, position ->
            loadImage(mContext, model.cover)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.article_default_image)
                    .error(R.drawable.article_default_image)
                    .centerCrop()
                    .dontAnimate()
                    .into(itemView)
        })
//        tips.clear()
        val tips = item.banner.data.map { it -> it.title }
        bgaBanner.setData(item.banner.data, tips)

    }

    fun setSapn() {
        if (mData != null && mData!!.size > 0)
            setSpanSizeLookup(object : BaseQuickAdapter.SpanSizeLookup {
                override fun getSpanSize(gridLayoutManager: GridLayoutManager, position: Int): Int {
                    return data?.get(position).span
                }
            })
    }
}
