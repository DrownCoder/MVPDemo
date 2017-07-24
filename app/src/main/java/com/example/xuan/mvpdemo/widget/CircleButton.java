package com.example.xuan.mvpdemo.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.xuan.mvpdemo.R;

/**
 * Author : xuan.
 * Data : 2017/7/24.
 * Description :Circular rotating button;
 */

public class CircleButton extends View {
    private int mCircleRadius;
    private int mCircleColor;
    private int mIndexRadius;
    private int mIndexColor;

    private Paint mPaintCircle;
    private Paint mPaintIndex;

    private float mDownX;
    private float mDownY;
    private float mMoveX;
    private float mMoveY;

    private float mCenterX;
    private float mCenterY;


    public CircleButton(Context context) {
        this(context, null);
    }

    public CircleButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleButton);
        mCircleRadius = a.getDimensionPixelSize(R.styleable.CircleButton_circleRadius, 20);
        mCircleColor = a.getColor(R.styleable.CircleButton_circleBac, Color.WHITE);
        mIndexRadius = a.getDimensionPixelSize(R.styleable.CircleButton_indexRadius, 5);
        mIndexColor = a.getColor(R.styleable.CircleButton_indexBac, Color.BLACK);
        a.recycle();

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(mCircleColor);
        mPaintCircle.setStyle(Paint.Style.FILL);


        mPaintIndex = new Paint();
        mPaintIndex.setAntiAlias(true);
        mPaintIndex.setColor(mIndexColor);
        mPaintIndex.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height;
        int width;

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = getPaddingTop() + 2 * mCircleRadius + getPaddingBottom();
        }
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = getPaddingLeft() + 2 * mCircleRadius + getPaddingRight();
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCenterX = getWidth() / 2.0f;
        mCenterY = getHeight() / 2.0f;
        canvas.drawCircle(mCenterX, mCenterY, mCircleRadius, mPaintCircle);
        canvas.drawCircle(getWidth() * 0.75f, getHeight() * 0.25f, mIndexRadius, mPaintIndex);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int direction = 1;
        ObjectAnimator animator;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mDownX = x;
                mDownY = y;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mMoveX = x;
                mMoveY = y;
                if (mMoveX < mCenterX) {
                    direction = -1;
                }
                animator = ObjectAnimator.ofFloat(this, "rotation", 0, 360 * direction);
                animator.setDuration(500);
                animator.start();
                break;
            }
        }
        return true;
    }

}
