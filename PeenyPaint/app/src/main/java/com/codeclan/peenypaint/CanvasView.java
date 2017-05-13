package com.codeclan.peenypaint;

//variables begin with 'm' as they are non-public and non-static

import android.content.Context;
//import android.support.annotation.Nullable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View{

    public int width;
    public int height;

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;

    private float mX, mY;

    Context context;

//    the below compares floats - if the TOLERANCE is below the given amount, they are considered equal
    private static final float TOLERANCE = 5;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        below we set 'this' to equal the above context
        this.context = context;

        mPath = new Path();

        mPaint = new Paint();

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.MAGENTA);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(5f);

    }

//    below - setting up Bitmap (the drawing surface)

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    private void firstTouch(float x, float y){
        mPath = moveTo(x, y);
        mX = x;
        mY = y;
    }

//    moving method - that includes a catch that resets the value of x and y if they exceed TOLERANCE (set above)
    private void moveTouch(float x, float y){
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if(dx >= TOLERANCE || dy >= TOLERANCE){
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) /2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas(){
        mPath.reset();
        invalidate();
    }

    private void upTouch(){
        mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN;
                firstTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE;
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP;
                upTouch();
                invalidate();
                break;

        }

        return true;

    }
}
