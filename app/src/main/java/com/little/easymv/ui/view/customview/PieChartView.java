package com.little.easymv.ui.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PieChartView extends View {

    public PieChartView(Context context) {
        super(context);
        initPaint();
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    Paint paint;

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE); //黄色
        paint.setTextSize(30);
        paint.setStrokeWidth(3);
//        paint.setColor(Color.parseColor("#9C27B0")); //紫色
    }

    int height;
    int width;

    int Radius;

    int centerX;
    int centerY;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
        Radius = Math.min(height,width)/2 - 150;
        centerX = width/2;
        centerY = height/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(canvas == null) {
            return;
        }
//        canvas.drawa
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(centerX,centerY);
        Rect rect = new Rect();
        paint.getTextBounds("LOLLPOP",0,"LOLLPOP".length(),rect);
        Path path = new Path();
        path.moveTo(-30,-30);
        path.lineTo(-(float)(Radius * Math.sin(45)) - 30.0f,-(float)(Radius * Math.sin(45)) - 30.0f);
        path.lineTo(-Radius  - 50,-(float)(Radius * Math.sin(45)) - 30.0f);
//        boolean fillPath = paint.getFillPath();
        canvas.drawPath(path,paint);
        //        综合练习
        //        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        canvas.drawText("LOLLPOP",-Radius - rect.width() -70,-(float)(Radius * Math.sin(45)) - 15.0f,paint);

        paint.setStyle(Paint.Style.FILL);
        RectF oval = new RectF( - Radius, - Radius, Radius,Radius);
//        RectF ovalUP = new RectF(centerX - Radius - 25 ,centerY - Radius -25,centerX +Radius - 25,centerY+ Radius - 25);
        paint.setColor(Color.parseColor("#9C27B0"));  //紫色
        canvas.drawArc(oval,0,10,true,paint);
        paint.setColor(Color.parseColor("#FFC107"));//黄色
        canvas.drawArc(oval,15,25,true,paint);
        paint.setColor(Color.parseColor("#009688"));
        canvas.drawArc(oval,45,125,true,paint);
        canvas.save();//旋转前保存当前canvas的位置
        canvas.translate(-25,-25);
        paint.setColor(Color.parseColor("#F44336")); //红色
        canvas.drawArc(oval,175,125,true,paint);
        canvas.restore();//返回刚才保存的位置
        paint.setColor(Color.parseColor("#2196F3"));
        canvas.drawArc(oval,305,50,true,paint);

//        canvas.restore();
    }

}
