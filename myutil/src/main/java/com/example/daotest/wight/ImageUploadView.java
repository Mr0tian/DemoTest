package com.example.daotest.wight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * @author tian on 2019/12/30
 * 可定制图标的水波纹进度球
 */
public class ImageUploadView extends View {


    //水波纹路径
    private Path mWavePath1, mWavePath2;

    //每节波浪的宽度
    private int mItemWidth = 120;
    //波浪的偏移量
    private int mOffsetX1, mOffsetX2;

    private Paint mIconPaint;
    private Paint mBallPaint;
    private Paint mTextPaint;

    //整个View的宽高
    private float mWidth, mHeight;

    //当前进度(0~100)
    private int mProgress;
    //圆形遮罩
    private Bitmap mBallBitmap;
    //图标资源对象
    private Drawable mDrawable;
    //当前水位高度的纵坐标
    private int mWaterTop;
    //每节波浪上下波动的幅度
    private int mWaveHeight;
    public ImageUploadView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWavePath1.reset();
        int halfItem = mItemWidth / 2;
        //为了闭合整个View,否则波浪遮罩顶部显示不正常
        mWavePath1.moveTo(0,0);
        //必须先减去一个浪的宽度，以便第一遍动画能够刚好位移出一个波浪，形成无限波浪的效果
        mWavePath1.lineTo(mOffsetX1 - mItemWidth, mWaterTop);
      //  for(int i = )

    }
}
