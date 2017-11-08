package com.little.easymv.event

/**
 * Created by Littlezuo on 2017/9/4.
 */

class EventUI {
    var tagInt = -1
    var tagStr = ""

//    companion object {
//        val TO_MAIN = 0x1111
//        val TO_TOP =  0x2222
//
//    }

    constructor(tag: Int) {
        tagInt = tag
    }

    constructor(tag: String) {
        tagStr = tag
    }

    fun isOk(tag:Int): Boolean {
        return tagInt == tag
    }

    fun is2main(): Boolean {
        return TO_MAIN.equals(tagStr)
    }

    fun is2top(): Boolean {
        return TO_TOP.equals(tagStr)
    }

}
