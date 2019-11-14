package com.example.daotest.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;


/**
 * @author tian on 2019/11/13
 */
public class MyCanvesView  extends View {

    private Path path1 = new Path();
    private Path path2 = new Path();
    private Path path3 = new Path();
    private Path path4 = new Path();
    private Path path5 = new Path();
    private Path path6 = new Path();

    public MyCanvesView(Context context, AttributeSet set) {
        super(context, set);
    }

    private LinearGradient mShader = new LinearGradient(0f, 0f, 40f, 60f,
            new int[]{Color.RED, Color.GREEN, Color.BLUE,Color.YELLOW},
            null, Shader.TileMode.REPEAT);

    private RectF rect = new RectF();
    //定义画笔
    private Paint paint = new Paint();

    /**
     * 重写该方法,进行绘图
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把整张画布绘制成白色
        canvas.drawColor(Color.WHITE);
        //去齿锯
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4f);
        int viewWidth = this.getWidth();

        //绘制圆形
        canvas.drawCircle(viewWidth/10 + 10, viewWidth/10 + 10, viewWidth/10,paint);

        //绘制正方形
        canvas.drawRect(10f,viewWidth/5 + 20, viewWidth/5+20,viewWidth*2/5+20,paint);

        //绘制矩形
        canvas.drawRect(10f,viewWidth*2/5+30,
                viewWidth/5 + 10, viewWidth/2+30,paint);
        

    }
}
