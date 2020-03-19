package com.example.daotest.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

import java.io.File;

/**
 * @author tian on 2019/11/26
 */
public class PathTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathTextView(this));
    }

    private static class MyView extends View {

        private float phase;
        private PathEffect[] effects = new PathEffect[7];
        private int[] colors;
        private Paint paint = new Paint();
        //定义并初始化path
        private Path path = new Path();

        public MyView(Context context) {
            super(context);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);
            path.moveTo(0f,0f);
            for (int i=1;i<= 40;i++){
                //生成40个点,随机生成它们的Y坐标,并将它们连成一条Path
                path.lineTo(i*25f, (float) (Math.random()*90));
            }

            //初始化7种颜色
            colors = new int[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW};

            //-----------------------下面开始初始化7种路径效果----------------------------------------
            //不适用路径效果
            effects[0] = null;
            //使用CornerPathEffect路径效果
            effects[1] = new CornerPathEffect(10f);
            //初始化DiscretePathEffect
            effects[2] = new DiscretePathEffect(3.0f,5.0f);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            //将背景填充成白色
            canvas.drawColor(Color.WHITE);
            //将画布移动到(8,8)处开始绘制
            canvas.translate(8f,8f);
            //依次使用7中不同的路径效果,7种不同的颜色来绘制路径
            for (int i=0; i<effects.length; i++){
                paint.setPathEffect(effects[i]);
                paint.setColor(colors[i]);
                canvas.drawPath(path, paint);
                canvas.translate(0f,90f);
            }

            //初始化DashPathEffect
            effects[3] = new DashPathEffect(new float[]{20f, 10f, 5f, 10f}, phase);
            //初始化 PathDashPathEffect
            Path p = new Path();
            p.addRect(0f, 0f, 8f, 8f, Path.Direction.CCW);
            effects[4] = new PathDashPathEffect(p, 12f, phase, PathDashPathEffect.Style.ROTATE);
            //初始化ComposePathEffect
            effects[5] = new ComposePathEffect(effects[2], effects[4]);
            effects[6] = new SumPathEffect(effects[4], effects[3]);
            //改变phase值,形成动画
            phase += 1f;
            invalidate();

        }
    }


    private static class PathTextView extends View{

        private String drawStr = "疯狂Android讲义";
        private Path[] paths = new Path[3];
        private Paint paint = new Paint();

        public PathTextView(Context context) {
            super(context);
            paths[0] = new Path();
            paths[0].moveTo(0f,0f);
            for (int i=1; i<=20; i++){
                //生成20个点,随机生成它们的Y坐标,并将它们练成一条Path
                paths[0].lineTo(i * 30f, (float) (Math.random() * 30));
            }
            paths[1] = new Path();
            RectF rectF = new RectF(0f, 0f, 600f, 360f);
            paths[1].addOval(rectF, Path.Direction.CCW);
            paths[2] = new Path();
            paths[2].addArc(rectF, 60f,180f);
            //初始化画笔
            paint.setAntiAlias(true);
            paint.setColor(Color.CYAN);
            paint.setStrokeWidth(1f);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            canvas.translate(40f, 40f);
            //设置从右边开始绘制(右对齐)
            paint.setTextAlign(Paint.Align.RIGHT);
            paint.setTextSize(30f);
            //绘制路径
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[0],paint);
            paint.setTextSize(40f);
            //沿着路径绘制一段文字
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(drawStr, paths[0], -8f,20f, paint);
            //对Canvas进行坐标变换:画布下移60
            canvas.translate(0f, 60f);
            //绘制路径
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[1],paint);
            //沿着路径绘制一段文本
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(drawStr, paths[1], -20f,20f,paint);
            // 对Canvas进行坐标变换: 画布下移360
            canvas.translate(0f,360f);
            //绘制路径
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[2], paint);
            //沿着路劲绘制一段文本
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(drawStr, paths[2],-10f, 20f, paint);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 选用AUTO页面采集模式
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
