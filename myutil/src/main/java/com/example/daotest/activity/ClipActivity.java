package com.example.daotest.activity;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author tian on 2019/10/10
 */
@SuppressWarnings("ALL")
public class ClipActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip);
        ImageView imageView = findViewById(R.id.image);
        //获取图片所显示的ClipDrawable对象
        final ClipDrawable drawable = (ClipDrawable) imageView.getDrawable();
        class MyHander extends Handler {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0x1233){
                    //修改ClipDrawable的level值
                    drawable.setLevel(drawable.getLevel()+10);
                }
            }
        }
        final Handler handler = new MyHander();
        try {
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 0x1233;
                    //发送消息,通知修改
                    handler.sendMessage(msg);
                    //取消定时器
                    if (drawable.getLevel() >= 10000){
                        timer.cancel();
                    }
                }
            },0,10);
        }catch (Exception e){

        }


        ImageView animImage = findViewById(R.id.img_anim);
        //加载动画资源
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.my_anim);
        //设置动画结束后保留结束状态
        anim.setFillAfter(true);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(view -> {
            animImage.startAnimation(anim);
        });


    }
}
