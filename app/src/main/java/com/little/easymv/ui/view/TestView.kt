package com.little.easymv.ui.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View

/**
 * Created by Littlezuo on 2017/9/26.
 */
class TestView : View {
    lateinit var paint: Paint;
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 5f
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(100f,100f,300f,300f,paint)
//        canvas?.drawCircle()
       var path =  Path()
        paint.style = Paint.Style.STROKE
        path.lineTo(100f,100f)
        path.arcTo(100f,100f,300f,300f,-90f,180f,true)
        paint.color = Color.RED
        canvas?.drawPath(path,paint)

    }
}