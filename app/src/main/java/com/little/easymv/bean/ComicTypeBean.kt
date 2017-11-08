package com.little.easymv.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Created by Littlezuo on 2017/9/6.
 */
class ComicTypeBean:MultiItemEntity {
    var type = HEADER;

    constructor(type: Int) {
        this.type = type
    }

    override fun getItemType(): Int {
       return type
    }


    companion object {
        val HEADER = 0
        val CHAPTER = 1
        val COMMENT = 2
    }

}