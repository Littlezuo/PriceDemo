package com.little.easymv.adapter

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.little.easymv.responsebean.RecommendResponse

/**
 * Created by Littlezuo on 2017/8/31.
 */

class FormatRecomBean :MultiItemEntity{

    private var itemType = BANNER


    override fun getItemType(): Int {
        return itemType
    }



    fun getSpanSize(): Int{
        return span
    }
    var childitem: RecommendResponse.DataBean? = null
    lateinit var banner: RecommendResponse
    var title: String? = null
    var span: Int = ALLSPAN


    constructor(recommendResponse: RecommendResponse) {
        banner = recommendResponse
        span = ALLSPAN
        itemType = BANNER
    }
    var isTitle = false
    constructor(title: String?) {
        this.title = title
        span = ALLSPAN
        this.isTitle = true
        itemType = TITLE
    }

    var hasSubTitle = true
    constructor(childitem: RecommendResponse.DataBean?, span: Int) {
        this.childitem = childitem
        this.span = span
        if (span == 3) {
            hasSubTitle = false
            itemType = BIG_IMG
        }else{
            itemType = SIMALL_IMG
        }

    }

    companion object {
        val ALLSPAN = 6

        //itemType;
        val BANNER = 0x001
        val SIMALL_IMG = 0x002
        val BIG_IMG = 0x003
        val TITLE = 0x004
    }
}
