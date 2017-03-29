package com.diguagege.heartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linhanwei on 2017/3/27.
 */

public class HeartView extends View {
    private Paint mCirclePaint;
    private int mStartPointX = 100;
    private int mStartPointY = 100;
    private int mCenterX;
    private int mCenterY;
    private int mStartX = 5;
    private int mStartY = 3;
    private Circle[][] mCircles;

    public HeartView(Context context) {
        super(context);
        initView();
    }

    public HeartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public HeartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setAntiAlias(true);
        mCircles = new Circle[10][9];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mCenterX = getWidth() / 7;
        mCenterY = getHeight() / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        drawHeart(canvas, mStartX, mStartY, 0);
        for (Circle[] circles : mCircles) {
            for (Circle circle : circles) {
                if (circle != null) {
                    circle.setNarrowRadius(Circle.NARROW_RADIUS);
                    circle.setStartAnim(false);
                }
            }
        }

        invalidate();
    }

    private void drawHeart(Canvas canvas, int x, int y, int drawOrder) {
        if (!isDisplay(x, y))
            return;

        Circle circle = mCircles[x][y];
        if (circle == null) {
            circle = new Circle();
            circle.setDelay(drawOrder * 3);
            drawOrder++;
            mCircles[x][y] = circle;
        } else {
            if (!circle.isStartAnim()) {
                circle.startCircleAnimation(circle);
                circle.setStartAnim(true);
            }
        }

        circle.setNarrowRadius(circle.getNarrowRadius() + drawOrder * 0.1f);
        canvas.drawCircle(mCenterX + x * mStartPointX, mCenterY + y * mStartPointY, circle.getRadius(), mCirclePaint);

        if (x > 1 && x <= 5) {
            drawHeart(canvas, x - 1, y, drawOrder);
        }

        if (x < 9 && x >= 5) {
            drawHeart(canvas, x + 1, y, drawOrder);
        }

        if (y > 1 && y <= 3) {
            drawHeart(canvas, x, y - 1, drawOrder);
        }

        if (y < 8 && y >= 3) {
            drawHeart(canvas, x, y + 1, drawOrder);
        }
    }

    private boolean isDisplay(int x, int y) {
        if ((x == 1 && (y == 1 || y == 5 || y == 6 || y == 7 || y == 8))
                || (x == 2 && (y == 6 || y == 7 || y == 8))
                || (x == 3 && (y == 7 || y == 8))
                || (x == 4 && y == 8)
                || (x == 5 && y == 1)
                || (x == 6 && y == 8)
                || (x == 7 && (y == 7 || y == 8))
                || (x == 8 && (y == 6 || y == 7 || y == 8))
                || (x == 9 && (y == 1 || y == 5 || y == 6 || y == 7 || y == 8)))
            return false;
        return true;
    }
}
