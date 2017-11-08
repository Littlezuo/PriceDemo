package com.little.easymv.extension

import com.jaydenxiao.common.baseevent.EventBusUtil
import com.little.easymv.adapter.FormatRecomBean
import com.little.easymv.responsebean.RecommendResponse

/**
 * Created by Littlezuo on 2017/8/31.
 */

fun formatRecom(list: List<RecommendResponse>): MutableList<FormatRecomBean> {
    val recoms = mutableListOf<FormatRecomBean>()
    recoms.add(FormatRecomBean(list.get(0)))
    val subList = list.subList(1, list.size)
    for (item in subList) {
        recoms.add(FormatRecomBean(item.title))
        var span = 2
        for (childitem in item.data) {
            when (item.data.size % 3) {
                1 -> span = 3
//                2 -> span =
            }
            recoms.add(FormatRecomBean(childitem, span))
        }
    }
    return recoms
}

fun registerEventBus(any: Any) {
    EventBusUtil.register(any)
}

fun unregisterEventBus(any: Any) {
    EventBusUtil.register(any)
}

fun postMessage(event: Any) {
    EventBusUtil.post(event)
}

fun calculateSpan(length: Int?): Int {
    var span = 4
    if (length != null) {
        span = if (length!! > 5) 3 else 4
        when (length!!) {
            in 0..3 -> span = 4
            in 4..7 -> span = 3
            in 8..12 -> span = 2
            else -> span = 1
        }
    }

    return span
}
//val TO_TOP = 0x33333;



