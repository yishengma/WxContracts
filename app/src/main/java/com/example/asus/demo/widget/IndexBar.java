package com.example.asus.demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.asus.demo.R;
import com.example.asus.demo.configs.Type;



public class IndexBar extends View {
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z","#"};
    private Paint mPaint;
    private int mHeight;//索引的高度
    private int mWidth;//索引条的宽度

    private float mLetterHeight ;//绘制的字母的高度
    private float mLetterWidth;//绘制的字母的宽度


    private int mIndex;//点击的字母的下标
    private  int mOldIndex;//上一次点击的字母的下标

    private TextView mLetterView;//用于绑定中间随索引显示的TextView


    private OnLetterChangeListener mLetterChangeListener;

    public interface OnLetterChangeListener{
        void  OnLetterChange(String index);
    }
    public IndexBar(Context context) {
        this(context,null);
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setColor(Color.parseColor("#FF585858"));
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14f,getResources().getDisplayMetrics()));
        mPaint.setTypeface(Typeface.DEFAULT);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        mLetterHeight = mHeight *1.0f/LETTERS.length;
        mLetterWidth = mWidth;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<LETTERS.length;i++){
            String letter = LETTERS[i];
            //x 的坐标以View 为坐标系
            int x = (int)(mLetterWidth/2.0f-mPaint.measureText(letter)/2.0f);

            Rect bounds = new Rect();
            mPaint.getTextBounds(letter,0,letter.length(),bounds);
            int y = (int)(mLetterHeight/2.0f+bounds.height()/2.0f+mLetterHeight*i);
            canvas.drawText(letter,x,y,mPaint);


        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mIndex = (int) (event.getY()/mLetterHeight);
                if (mIndex!=mOldIndex&&mIndex>=0&&mIndex<LETTERS.length){
                    if (mLetterChangeListener!=null){
                        mLetterChangeListener.OnLetterChange(LETTERS[mIndex]);
                        mLetterView.setText(LETTERS[mIndex]);
                        mLetterView.setVisibility(VISIBLE);
                        this.setBackgroundResource(R.drawable.drawableIndexViewBackground);
                    }
                }
                mOldIndex = mIndex;
                break;
            case MotionEvent.ACTION_MOVE:
                mIndex = (int) (event.getY()/mLetterHeight);
                if (mIndex!=mOldIndex&&mIndex>=0&&mIndex<LETTERS.length){
                    if (mLetterChangeListener!=null){
                        mLetterChangeListener.OnLetterChange(LETTERS[mIndex]);
                        mLetterView.setText(LETTERS[mIndex]);
                        mLetterView.setVisibility(VISIBLE);
                        this.setBackgroundResource(R.drawable.drawableIndexViewBackground);

                    }
                }
                mOldIndex = mIndex;
                break;
            case MotionEvent.ACTION_UP:
                mLetterView.setVisibility(INVISIBLE);
                this.setBackgroundResource(R.drawable.drawableIndexView);
                mOldIndex = -1;
                break;
                default:
                    mLetterView.setVisibility(INVISIBLE);
                    this.setBackgroundResource(R.drawable.drawableIndexView);

                    break;
        }
        invalidate();
        return true;
    }


    public void setLetterChangeListener(OnLetterChangeListener letterChangeListener) {
        mLetterChangeListener = letterChangeListener;
    }

    public void setLetterView(TextView letterView) {
        mLetterView = letterView;
    }
}
