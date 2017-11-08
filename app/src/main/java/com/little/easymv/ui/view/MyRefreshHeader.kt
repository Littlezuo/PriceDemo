package com.little.easymv.ui.view

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jaydenxiao.common.commonutils.DisplayUtil
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.internal.ProgressDrawable
import com.scwang.smartrefresh.layout.internal.pathview.PathsView
import com.scwang.smartrefresh.layout.util.DensityUtil

/**
 * Created by Littlezuo on 2017/9/26.
 */
open class MyRefreshHeader : LinearLayout, RefreshHeader {


    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Scale
    }

    override fun onFinish(layout: RefreshLayout?, success: Boolean): Int {
        mProgressDrawable.stop()
        if (success) {
            mHeaderText.setText("刷新完成")
        } else {
            mHeaderText.setText("刷新失败")
        }
        return 500 //延迟500ms再回弹
    }

    override fun onStateChanged(refreshLayout: RefreshLayout?, oldState: RefreshState?, newState: RefreshState?) {
        when(newState) {
            RefreshState.PullDownToRefresh -> {
                mHeaderText.text = "使劲儿.."
                mArrowView.visibility = View.VISIBLE //显示箭头
                mProgressView.visibility = View.GONE //隐藏刷新动画
                val animator = mArrowView.animate().rotation(180f)
//                animator.duration = 300
//                ViewCompat.animate(mArrowView)
            }
            RefreshState.Refreshing -> {
                mHeaderText.text = "咔咔咔加载中..."
                mProgressView.visibility = View.VISIBLE
                mArrowView.visibility = View.GONE
            }
            RefreshState.ReleaseToRefresh -> {
                mHeaderText.text = "放手是一种智慧"
                ViewCompat.animate(mArrowView).rotation(180f)//显示箭头改为朝上
            }
        }
    }

    override fun onInitialized(kernel: RefreshKernel?, height: Int, extendHeight: Int) {
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {
    }

    override fun getView(): View {

        return this
    }

    override fun setPrimaryColors(vararg colors: Int) {
    }

    override fun onReleasing(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {
    }

    override fun onStartAnimator(layout: RefreshLayout?, height: Int, extendHeight: Int) {
        mProgressDrawable.start()
    }


    override fun onPullingDown(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    constructor(context: Context?) : super(context) {
        initView(context)
    }

    lateinit var mProgressDrawable: ProgressDrawable
    lateinit var mHeaderText: TextView
    lateinit var mArrowView :PathsView
    lateinit var mProgressView :ImageView
    private fun initView(context: Context?) {
        setBackgroundColor(Color.WHITE)
        gravity = Gravity.CENTER
        mHeaderText = TextView(context)
        mProgressDrawable = ProgressDrawable()
        mArrowView = PathsView(context)
        mProgressView = ImageView(context)
        mProgressView.setImageDrawable(mProgressDrawable)
        mArrowView.parserPaths("M20,12l-1.41,-1.41L13,16.17V4h-2v12.17l-5.58,-5.59L4,12l8,8 8,-8z")
        addView(mProgressView,DisplayUtil.dip2px(20f),DisplayUtil.dip2px(20f))
        addView(mArrowView,DisplayUtil.dip2px(18f),DisplayUtil.dip2px(18f))
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(DensityUtil.dp2px(20f), 0, 0, 0)
        addView(mHeaderText, /*LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,*/layoutParams)
        minimumHeight = DensityUtil.dp2px(60f)


    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

}