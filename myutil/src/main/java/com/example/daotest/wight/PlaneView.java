package com.example.daotest.wight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.daotest.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author tian on 2019/9/17
 */
public class PlaneView extends View {

    public float currentX;
    public float currentY;

    /**
     * 创建画笔
     */
    private Paint p = new Paint();
    private Bitmap plane0;
    private Bitmap plane1;
    private int index;

    /**
     *
     * @param context
     */
    public PlaneView(Context context) {
        super(context);
        //定义飞机的图片
        plane0 = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane0);
        plane1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane1);
        //启动定时器来切换飞机图片,实现动画效果
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                index++;
                PlaneView.this.invalidate();
            }
        },0,100L);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制飞机
        canvas.drawBitmap(index%2 == 0 ? plane0 : plane1,currentX,currentY,p);
    }
}
