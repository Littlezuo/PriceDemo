package com.little.easymv.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseViewHolder
import com.jaydenxiao.common.baseadapter.MyBaseQuickAdapter
import com.little.easymv.R
import com.little.easymv.extension.loadCircleImage
import com.little.easymv.responsebean.CategoryResponse
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Created by Littlezuo on 2017/9/4.
 */
class CateGoryAdapter : MyBaseQuickAdapter<CategoryResponse> {
    constructor(data: MutableList<CategoryResponse>?, recyclerView: RecyclerView) : super(R.layout.item_category,data, recyclerView) {
//        setOnItemClickListener()

    }

    override fun convert(helper: BaseViewHolder, itemView: View, item: CategoryResponse) {
//        helper.setText(R.id.category_tv,item.title)
        itemView.category_tv.text =item.title
        val imageView = helper.getView<ImageView>(R.id.category_iv)
        loadCircleImage(mContext,imageView,item.cover)
//        helper.setOnClickListener()

    }

}