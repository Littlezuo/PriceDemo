package com.little.easymv.ui.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jaydenxiao.common.commonutils.DisplayUtil
import com.little.easymv.R


/**
 * Created by Littlezuo on 2017/9/6.
 */
class MoreTextView : LinearLayout {
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initalize()
        initWithAttrs(context, attrs)
        bindListener()
    }

    /**
     * 绑定点击事件并设置动画
     */
    private fun bindListener() {
        var isExpand: Boolean = false //internal：同模块可见
        setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View) {
                isExpand = !isExpand
                contentView.clearAnimation()
                val deltaValue: Int
                val startValue = contentView.height
                val durationMillis = 350
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = contentView.lineHeight * contentView.lineCount - startValue
                    val animation = RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                    animation.duration = durationMillis.toLong()
                    animation.fillAfter = true
                    expandView.startAnimation(animation)
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = contentView.lineHeight * maxLine - startValue
                    val animation = RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                    animation.duration = durationMillis.toLong()
                    animation.fillAfter = true
                    expandView.startAnimation(animation)
                }
                val animation = object : Animation() {
                    protected override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                        //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        contentView.height = (startValue + deltaValue * interpolatedTime).toInt()
                    }

                }
                animation.setDuration(durationMillis.toLong())
                contentView.startAnimation(animation)
            }
        })
    }


    /**
     * 取值设置
     */
    val defaultTextColor = Color.GRAY
    private val defaultTextSize: Int = 12

    var textSize: Int = 12

    private val defaultLine: Int = 2

    private var maxLine: Int = 2

    private var text: String = ""

    private fun initWithAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MoreTextStyle)
        val textColor = typedArray.getColor(R.styleable.MoreTextStyle_textColor, defaultTextColor)
        textSize = typedArray.getDimensionPixelSize(R.styleable.MoreTextStyle_textSize, defaultTextSize)
        maxLine = typedArray.getInt(R.styleable.MoreTextStyle_maxLine, defaultLine)
        text = typedArray.getString(R.styleable.MoreTextStyle_text)
        bindTextView(textColor,textSize.toFloat(),maxLine,text)
        typedArray.recycle()
    }

    //绑定到textView
    protected fun bindTextView(color: Int, size: Float, line: Int, text: String) {
        contentView.setTextColor(color)
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
        contentView.setText(text)
        contentView.setHeight(contentView.getLineHeight() * line)
        post {
            expandView.visibility = if (contentView.getLineCount() > line) View.VISIBLE else View.GONE
        }
    }


    /**
     * 初始化并添加view
     */
    lateinit var contentView: TextView
    private lateinit var expandView: ImageView


    private fun initalize() {
        orientation = VERTICAL
        gravity = Gravity.RIGHT
        //初始化textview并添加
        contentView = TextView(context)
        addView(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        //初始化ImageView并添加
        expandView = ImageView(context)
        val padding = DisplayUtil.dip2px(5f)
        expandView.setPadding(padding, padding, padding, padding)
        expandView.setImageResource(R.drawable.detail_intr_expand)
        val llp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        addView(expandView, llp)
    }

    fun getTextView(): TextView {
        return contentView
    }

    fun setText(charSequence: CharSequence) {
        if (charSequence != null) {
            contentView.text = charSequence

        }
    }

}