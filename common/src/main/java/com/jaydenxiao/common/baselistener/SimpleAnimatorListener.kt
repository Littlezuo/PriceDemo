package com.jaydenxiao.common.baselistener

import android.animation.Animator

/**
 * Created by Littlezuo on 2017/8/25.
 */

open class SimpleAnimatorListener:Animator.AnimatorListener{
    /**
     *
     * Notifies the repetition of the animation.
     *
     * @param animation The animation which was repeated.
     */
    override fun onAnimationRepeat(animation: Animator?) {
    }

    /**
     *
     * Notifies the end of the animation. This callback is not invoked
     * for animations with repeat count set to INFINITE.
     *
     * @param animation The animation which reached its end.
     */
    override fun onAnimationEnd(animation: Animator?) {
    }

    /**
     *
     * Notifies the cancellation of the animation. This callback is not invoked
     * for animations with repeat count set to INFINITE.
     *
     * @param animation The animation which was canceled.
     */
    override fun onAnimationCancel(animation: Animator?) {
    }

    /**
     *
     * Notifies the start of the animation.
     *
     * @param animation The started animation.
     */
    override fun onAnimationStart(animation: Animator?) {
    }
}
