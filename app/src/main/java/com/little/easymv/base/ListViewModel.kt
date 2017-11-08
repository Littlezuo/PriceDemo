package com.little.easymv.base

import com.jaydenxiao.common.baseevent.BindBus
import com.jaydenxiao.common.baseevent.EventBusUtil
import com.jaydenxiao.common.basemvvm.ViewModel
import com.little.easymv.event.EventUI
import org.greenrobot.eventbus.Subscribe

//import com.jaydenxiao.common.baseevent.rxbus.Subscribe;

//import com.jaydenxiao.common.baseevent.rxbus.RxBus;

/**
 * Created by Littlezuo on 2017/9/1.
 * 传该类作为泛型自动注册rxbus,
 */

@BindBus
abstract class ListViewModel : ViewModel() {
    override fun onStart() {
        super.onStart()
        //        RxBus.getDefault().register(this);
        EventBusUtil.register(this)
    }


    abstract fun scroll2top()

    override fun onDestory() {
        super.onDestory()
        //        RxBus.getDefault().unRegister(this);
        EventBusUtil.unregister(this)
    }

    //    companion object {
//        val TO_TOP = 0x99919
//    }
//
    @Subscribe
    fun onEvent(eventUI: EventUI) {
        if (eventUI.is2top()) {
            if (mFragment != null) {
                if (mFragment.isVisible)
                    scroll2top()
            } else {
                scroll2top()
            }
        }

    }


}
