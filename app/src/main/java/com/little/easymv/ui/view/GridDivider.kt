package com.little.easymv.ui.view

import android.graphics.Color
import com.jaydenxiao.common.baseadapter.UniversalItemDecoration
import com.jaydenxiao.common.commonutils.DisplayUtil

/**
 * Created by Littlezuo on 2017/9/6.
 */
class GridDivider : UniversalItemDecoration() {
    override fun getItemOffsets(position: Int): Decoration {
        var decoration = ColorDecoration()
        decoration.decorationColor = Color.TRANSPARENT
        decoration.top = DisplayUtil.dip2px(4f)

        if (position % 3 == 1) {//中间
            decoration.left = DisplayUtil.dip2px(2f)
            decoration.right = DisplayUtil.dip2px(2f)
        }else if (position % 3 == 0) { //左边
            decoration.left = DisplayUtil.dip2px(3f)
            decoration.right = DisplayUtil.dip2px(1f)
        }else{
            decoration.left = DisplayUtil.dip2px(1f)
            decoration.right = DisplayUtil.dip2px(3f)
        }
        return decoration
    }

}