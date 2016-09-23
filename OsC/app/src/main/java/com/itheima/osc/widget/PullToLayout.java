package com.itheima.osc.widget;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/9/17.
 */
public class PullToLayout extends LinearLayout {
    int mDrag = 0;
    private int mDownY;
    private int mMoveY;
    private  Scroller mScoller;

    public PullToLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScoller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取里面的子控件
        View view = getChildAt(0);
        view.layout(l,t+mDrag,r,b+mDrag);
    }
    //重写ontoouch事件

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //获取当前压下坐标
                mDownY = (int)event.getRawY();
                System.out.println("mDownY : "+mDownY);
                break;
            case MotionEvent.ACTION_MOVE:
                //获取移动的y
                mMoveY = (int)event.getRawY();
                if(mMoveY -mDownY>0){
                    mDrag = (mMoveY -mDownY)/5;
                }else {
                    mDrag = 0;
                }
                requestLayout();
                System.out.println(mDrag);
                break;
            case MotionEvent.ACTION_UP:
                //抬起的时候
                startAnimator();


                mDrag = 0;
                break;
        }


        return true;
    }

    private void startAnimator() {
        //在这里实现一个缓冲，用一个值动画
        ValueAnimator va = ValueAnimator.ofFloat(mDrag,0);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //睡一下
                SystemClock.sleep(10);
                float animatedValue = (float) animation.getAnimatedValue();
                mDrag = (int) animatedValue;
                requestLayout();
            }
        });
        va.start();
    }


    @Override
    public void computeScroll() {
        if(mScoller.computeScrollOffset()){
            invalidate();
        }
    }
}
