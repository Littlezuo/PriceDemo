package com.little.easymv.ui.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class HistogramView extends View {

    private Paint mPaint;

    public HistogramView(Context context) {
        super(context);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(28);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //        综合练习
        //        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        if (canvas == null)
            return;

        canvas.drawLine(50, 500, 650, 500, mPaint);
        canvas.drawLine(50, 50, 50, 500, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(600, 480);
        path.lineTo(650, 500);
        path.lineTo(600, 520);
        canvas.drawPath(path, mPaint);

        Path path1 = new Path();
        path1.moveTo(30, 100);
        path1.rLineTo(20, -50);
        path1.rLineTo(20, 50);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path1, mPaint);
        drawRect(50, 1, canvas, "Lollipop");
        drawRect(150, 2, canvas, "CK");
        drawRect(220, 3, canvas, "GD");
        drawRect(310, 4, canvas, "M");
    }

    private void drawRect(int height, int index, Canvas canvas, String text) {
        mPaint.setColor(Color.GREEN);
        int chartWidth = 100;
        RectF rectF = new RectF(50 + 20 * index + chartWidth * (index - 1), 500 - height, 50 + 20 * index + chartWidth * (index - 1) + chartWidth, 500);
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.GRAY);
        float top = mPaint.getFontMetrics().top;//最顶线距离
        float baseLine = 500 - top;  //计算基线
//        float width = mPaint.measureText(text);
//        float startLine = (50 + 20 * index + chartWidth * (index - 1)) + chartWidth/2 -
        mPaint.setTextAlign(Paint.Align.CENTER);//居中绘制
        canvas.drawText(text,50 + 20 * index + chartWidth * (index - 1) + chartWidth/2,baseLine,mPaint);
    }
}
