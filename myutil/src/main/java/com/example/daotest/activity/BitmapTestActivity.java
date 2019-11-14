package com.example.daotest.activity;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import java.io.InputStream;

import butterknife.InjectView;

/**
 * @author tian on 2019/11/13
 */
public class BitmapTestActivity extends AppCompatActivity {

    private String[] images;
    private int currentImg;
    private ImageView imageView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        imageView = findViewById(R.id.image);

        try{
            //获取 /assets/目录下的所有文件
            images = getAssets().list("");
        }catch (Exception e){
            e.printStackTrace();
        }

        //获取next按钮
        Button next = findViewById(R.id.btn_click);
        //为next按钮绑定事件监听器,该监听器将会查看下一张图片
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果发生数组越界
                if (currentImg >= images.length){
                    currentImg = 0;
                }
                //找到下一个图片文件
                while (!images[currentImg].endsWith(".png")&&
                       !images[currentImg].endsWith(".jpg")&&
                       !images[currentImg].endsWith(".gif")){
                    currentImg++;
                    //如果已发生数组越界
                    if (currentImg >= images.length){
                        currentImg = 0;
                    }
                }
                InputStream assetFile = null;
                try{
                    //打开指定资源对应的输入流
                    assetFile = getAssets().open(images[currentImg]);
                }catch (Exception e){
                    e.printStackTrace();
                }
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                //如果图片还未回收,先强制回收该图片
                if (bitmapDrawable != null && !bitmapDrawable.getBitmap().isRecycled()){
                    bitmapDrawable.getBitmap().recycle();
                }
                //改变ImageView显示的图片
                imageView.setImageBitmap(BitmapFactory.decodeStream(assetFile));
            }
        });

    }
}
