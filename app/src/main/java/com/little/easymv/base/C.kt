package com.little.easymv.base

import com.little.easymv.bean.MessageInfo
import com.little.easymv.bean._User

/**
 * Created by Littlezuo on 2017/8/28.
 */

object C {
    const val BASE_SCHEME = "wyx://"
    const val MAIN = "main"
    const val TURN_MAIN = "wyx://main"
    const  val FLASH = "flash"

    //==================API============//
    const val X_LC_Id = "i7j2k7bm26g7csk7uuegxlvfyw79gkk4p200geei8jmaevmx"
    const val X_LC_Key = "n6elpebcs84yjeaj5ht7x0eii9z83iea8bec9szerejj7zy3"
    const val BASE_URL = "https://leancloud.cn:443/1.1/"

    const val ADMIN_ID = "53d9076ce4b0ef69707fc78c"
    const  val ADMIN_FACE = "https://avatars0.githubusercontent.com/u/7598555?v=3&s=460"
    //==================Base============//
    const val PAGE_COUNT = 10
    const val FLAG_MULTI_VH = 0x000001
    const val OPEN_TYPE = "公开"

    //==================intent============//
    const val TRANSLATE_VIEW = "share_img"
    const val TYPE = "type"
    const val HEAD_DATA = "data"
    const val VH_CLASS = "vh"
    const val IMAGE_REQUEST_CODE = 100


    //==================API============//
    const val _CREATED_AT = "-createdAt"
    const val INCLUDE = "include"
    const val CREATER = "creater"
    const val UID = "uId"
    const val PAGE = "page"

    //==================Router============//
    const val HOME = "home"
    const val TAB = "tab"
    const val ARTICLE = "article"
    const val LOGIN = "login"
    const val ABOUT = "about"
    const val ADVISE = "advise"
    const val SETTING = "setting"
    const val USER_INFO = "userInfo"
    const val USER_RELEASE = "release"
    const val OBJECT_ID = "objectId"


    //==================static============//
    @JvmStatic
    fun getAdminMsg(): MessageInfo {
        val mMessageInfo = MessageInfo()
        mMessageInfo.message = "您好，我是本应用的开发者North，如果您有什么好的建议和反馈，可以在这里和我直接对话，谢谢你的支持哦"
        val admin = _User()
        admin.username = "North"
        admin.objectId = C.ADMIN_ID
        admin.face = ADMIN_FACE
        mMessageInfo.creater = admin
        return mMessageInfo
    }
    @JvmField
     var HOME_TABS = arrayOf("公开", "民谣", "摇滚", "电子", "流行", "爵士", "独立", "故事", "新世纪", "精品推荐", "原声")
}
