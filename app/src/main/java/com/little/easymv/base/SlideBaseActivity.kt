package com.little.easymv.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.jaydenxiao.common.baseapp.Router
import com.jaydenxiao.common.basemvvm.BaseActivity
import com.jaydenxiao.common.basemvvm.VMListener
import com.jaydenxiao.common.basemvvm.ViewModel

/**
 * Created by joyin on 17-4-12.
 * 侧滑退出activity
 */

abstract class SlideBaseActivity<M : ViewModel> : BaseActivity<M>(), VMListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Router.pop(this)
            return true
        } else {
            return super.onKeyDown(keyCode, event)
        }
    }


    fun back(v: View) {
        Router.pop(this)
    }
}
