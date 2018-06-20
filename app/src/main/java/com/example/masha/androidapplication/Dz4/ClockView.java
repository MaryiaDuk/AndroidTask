package com.example.masha.androidapplication.Dz4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class ClockView extends View {

    private Paint paint;
    private Paint paintInner;
    private Paint paintPoint;
    private Paint paintLines;
    private Paint paintText;
    private Calendar time;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // int color = Color.parseColor("#FEE1F1");
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(60);
        paint.setStyle(Paint.Style.STROKE);

        paintInner = new Paint();
        paintInner.setColor(Color.WHITE);

        paintLines = new Paint();
        paintLines.setColor(Color.BLACK);
        paintLines.setStrokeWidth(15);
        paintLines.setAntiAlias(true);


        paintPoint = new Paint();
        paintPoint.setColor(Color.BLACK);
        paintPoint.setStrokeWidth(10);

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(40);
        paintText.setTextAlign(Paint.Align.CENTER);

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        time = Calendar.getInstance();
        float hours = time.get(Calendar.HOUR);
        float minutes = time.get(Calendar.MINUTE);
        float seconds = time.get(Calendar.SECOND);

        float top = getHeight() / 2 - getWidth() / 2 + 70;

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getWidth(), getHeight()) / 2, paintInner);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getWidth(), getHeight()) / 2, paint);
        canvas.drawPoint(getWidth() / 2, getHeight() / 2, paintPoint);
        for (int i = 0; i < 12; i++) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                //   canvas.drawLine(getWidth() / 2, (getWidth() / 3 + 40), getWidth() / 2, getWidth() / 3 + 80, paintPoint);
                canvas.drawLine(getWidth() / 2, top, getWidth() / 2, top + 40, paintPoint);

            } else {
                canvas.drawLine(getWidth() / 2, 70, getWidth() / 2, 110, paintPoint);
            }
            canvas.rotate(30, getWidth() / 2, getHeight() / 2);
        }


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            canvas.drawText("12", getWidth() / 2, top + 70, paintText);
            canvas.drawText("3", getWidth() - 130, getHeight() / 2 + 10, paintText);
            canvas.drawText("6", getWidth() / 2, getHeight() - top - 70, paintText);
            canvas.drawText("9", 130, getHeight() / 2 + 10, paintText);
            //Минутная
            canvas.save();
            canvas.rotate(minutes * 6, getWidth() / 2, getHeight() / 2);
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - 20, getWidth() / 2, getWidth()*0.55f, paintLines);
            canvas.restore();
            //Часовая
            canvas.save();
            canvas.rotate(hours * 30 + minutes * 0.5f, getWidth() / 2, getHeight() / 2);
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - 20, getWidth() / 2, getWidth()*0.65f, paintLines);
            canvas.restore();
            //Секундная
            canvas.save();
            canvas.rotate(seconds * 6, getWidth() / 2, getHeight() / 2);
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - 20, getWidth() / 2, getHeight() / 2 - getWidth() / 3+30, paintPoint);
            canvas.restore();

        } else {

            canvas.drawText("12", getWidth() / 2,  130, paintText);
            canvas.drawText("3", getWidth()/2+getHeight()/2 - 130, getHeight() / 2 + 10, paintText);
            canvas.drawText("6", getWidth() / 2, getHeight()-130, paintText);
            canvas.drawText("9", getWidth()/2-getHeight()/2 +130, getHeight() / 2 + 10, paintText);
            canvas.save();
            canvas.rotate(minutes * 6, getWidth() / 2, getHeight() / 2);
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - 20, getWidth() / 2, getHeight() *0.22f,paintLines);
            canvas.restore();

            canvas.save();
            canvas.rotate(hours * 30 + minutes * 0.5f, getWidth() / 2, getHeight() / 2);
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - 20, getWidth() / 2, getHeight()*0.35f, paintLines);
            canvas.restore();

            canvas.save();
            canvas.rotate(seconds * 6, getWidth() / 2, getHeight() / 2);
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - 20, getWidth() / 2, getHeight()*0.18f, paintPoint);
            canvas.restore();

        }

        new ClockRunnable();
    }


    class ClockRunnable implements Runnable {
        Thread thread;

        ClockRunnable() {
            thread = new Thread(this, "ClockThread");
            thread.start();
        }

        public void run() {
            try {
                Thread.sleep(1000);
                invalidate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}