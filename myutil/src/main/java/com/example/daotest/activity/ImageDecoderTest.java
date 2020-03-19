package com.example.daotest.activity;

import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;
import com.example.daotest.wight.DrawView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author tian on 2019/11/26
 */
public class ImageDecoderTest extends AppCompatActivity {

    @InjectView(R.id.img)
    ImageView img;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_decoder);
        ButterKnife.inject(this);

        //1 创建ImageDecoder.Source对象
        ImageDecoder.Source source = ImageDecoder.createSource(getResources(),R.drawable.test);
        try{
            //执行decodeDrawable()方法获取Drawable对象
            @SuppressLint("WrongThread") Drawable drawable = ImageDecoder.decodeDrawable(
                    source,(decoder,info,s)->{

                    });

            img.setImageDrawable(drawable);
            //如果drawable是AnimatedImageDrawable的实例,则执行动画
            if (drawable instanceof AnimatedImageDrawable){
                ((AnimatedImageDrawable) drawable).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
