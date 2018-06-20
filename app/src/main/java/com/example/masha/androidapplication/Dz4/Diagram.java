package com.example.masha.androidapplication.Dz4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;

public class Diagram extends View {
    private Paint redPaint;
    private Paint bluePaint;
    private Paint greenPaint;
    private RectF rectF = new RectF();
    private float firstPart;
    private float secondPart;
    private float thirdPart;


    public Diagram(Context context) {
        super(context);
        initPaint();
    }

    public Diagram(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Diagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Diagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    private void initPaint() {
        redPaint = new Paint();
        redPaint.setAntiAlias(true);
        redPaint.setColor(Color.RED);

        bluePaint = new Paint();
        bluePaint.setAntiAlias(true);
        bluePaint.setColor(Color.BLUE);

        greenPaint = new Paint();
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(true);
    }

    public void setData(Editable a, Editable b, Editable c) {
        float sum = Float.valueOf(a.toString()) + Float.valueOf(b.toString()) + Float.valueOf(c.toString());
        float onePart = (Float.valueOf(a.toString()) * 100) / sum;
        float twoPart = (Float.valueOf(b.toString()) * 100) / sum;
        float threePart = (Float.valueOf(c.toString()) * 100) / sum;

        firstPart = 360 * onePart / 100;
        secondPart = 360 * twoPart / 100;
        thirdPart = 360 * threePart / 100;

        invalidate();
    }

    private void drawRect() {
        rectF.left = getWidth() * 0.05f;
        rectF.top = getHeight() * 0.05f;
        rectF.bottom = getHeight() - rectF.top;
        rectF.right = getWidth() - rectF.left;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect();
        canvas.drawArc(rectF, 0f, firstPart, true, redPaint);
        canvas.drawArc(rectF, firstPart, secondPart, true, bluePaint);
        canvas.drawArc(rectF, firstPart + secondPart, thirdPart, true, greenPaint);
    }
}