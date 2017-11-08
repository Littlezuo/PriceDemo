package com.little.easymv.ui.act


import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import com.jaydenxiao.common.baseapp.Router
import com.jaydenxiao.common.baseevent.BindBus
import com.jaydenxiao.common.basemvvm.BaseActivity
import com.little.easymv.vm.FlashMV
import com.little.easymv.R
import com.little.easymv.extension.postMessage
import com.little.easymv.event.EventUI
import com.little.easymv.event.TO_MAIN
import kotlinx.android.synthetic.main.activity_flash.*
import org.greenrobot.eventbus.Subscribe

@BindBus
class FlashActivity : BaseActivity<FlashMV>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_flash
    }


    override fun initData() {
//        RxBus.getDefault().post(100, EventA("textxxxx"))
        startAnimator()
    }


    private fun startAnimator() {
//        var alpha = PropertyValuesHolder.ofFloat("scaleX",0,1)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1.2f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1.2f)
//        val flash = findViewById(R.id.iv_flash)
        val animator = ObjectAnimator.ofPropertyValuesHolder(iv_flash, scaleX, scaleY)

        animator.setDuration(500)
        animator.start()
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
//                mRxManager.post(EventName.TO_MAIN, "")
                postMessage(EventUI(TO_MAIN))
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })

    }

    @Subscribe()
    open fun jump2main(tag: EventUI) {
        if (tag.is2main()) {
//            RouterManager.start(MainActivity::class.java)
            Router.from(this)
                    .anim(R.anim.screen_zoom_in, R.anim.screen_zoom_out)
                    .to(MainActivity::class.java)
                    .launch(true)

//            finish()
        }
    }

}
