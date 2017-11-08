package com.little.easymv.ui.view

import com.jaydenxiao.common.baseadapter.UniversalItemDecoration
import com.jaydenxiao.common.baseapp.BaseApplication
import com.jaydenxiao.common.commonutils.DisplayUtil
import com.little.easymv.R
import com.little.easymv.adapter.FormatRecomBean

/**
 * Created by Littlezuo on 2017/9/1.
 */

class RecomDivider : UniversalItemDecoration {
    private lateinit var FormatRecom: List<FormatRecomBean>

    constructor(formatRecom:List<FormatRecomBean>) {

        this.FormatRecom = formatRecom

    }
    override fun getItemOffsets(position: Int): UniversalItemDecoration.Decoration? {
        if (FormatRecom.size <= 0) {
            return null
        }
        val decoration = ColorDecoration()
        decoration.decorationColor = BaseApplication.getAppColor(R.color.divider)
        if(FormatRecom.get(position).isTitle){
            decoration.top = DisplayUtil.dip2px(10f);
        }
        return decoration
    }
}
