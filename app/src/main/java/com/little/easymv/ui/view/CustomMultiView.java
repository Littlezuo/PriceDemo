package com.little.easymv.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.little.easymv.R;

/**
 * Created by Littlezuo on 2017/9/6.
 */

public class CustomMultiView extends View {
    private Paint paint;
    private Path path;
    private int width, height;
    private int barColor = getResources().getColor(R.color.colorPrimary);

    public CustomMultiView(Context context) {
        this(context, null);
    }

    public CustomMultiView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomMultiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        parseAttributes(context.obtainStyledAttributes(attrs, R.styleable.PathmMultiView));
    }

    private void parseAttributes(TypedArray typedArray) {
        barColor = typedArray.getColor(R.styleable.PathmMultiView_path_backgroundColor, barColor);
        height = (int) typedArray.getDimension(R.styleable.PathmMultiView_path_height, height);
    }

    private void initPaint() {
        width = DisplayUtil.getScreenWidth(getContext());
        height = width / 2 - 50;
        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(0, 0);
        path.lineTo(0, height - DisplayUtil.dip2px(50));
        path.lineTo(width, height);
        path.lineTo(width, 0);
        paint.setStrokeWidth(3);
        path.close();
        paint.setColor(barColor);
        paint.setStyle(Paint.Style.FILL);   //设置画笔颜色包括填充色
        canvas.drawPath(path, paint);
    }
}
