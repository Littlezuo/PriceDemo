package com.little.easymv.app

import android.content.Context
import android.support.v4.content.ContextCompat
import com.jaydenxiao.common.baseapp.BaseApplication
import com.little.easymv.BuildConfig
import com.little.easymv.R
import com.little.easymv.constants.EnvirConfig
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * Created by Littlezuo on 2017/8/31.
 */

class MyApplication : BaseApplication() {
    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, layout ->
            val header = ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.FixedBehind)
            header.setPrimaryColors(ContextCompat.getColor(context, R.color.colorPrimary), ContextCompat.getColor(context, android.R.color.white))
            header//指定为经典Header，默认是 贝塞尔雷达Header
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater { context, layout ->
            layout.setEnableLoadmoreWhenContentNotFull(true)
            val footer = ClassicsFooter(context)
            footer.setBackgroundResource(android.R.color.white)
            footer.spinnerStyle = SpinnerStyle.Scale//设置为拉伸模式
            footer//指定为经典Footer，默认是 BallPulseFooter
        }
    }
    object C {
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()
        C.context = applicationContext
        EnvirConfig.setEnvir(BuildConfig.API_ENV)
        getAppContext()
    }

    //Companion Object是在类第一次加载时执行
    companion object {
        fun getAppContext(): Context {
            return C.context
        }
    }


}