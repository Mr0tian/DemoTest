package com.example.daotest.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Url 转Bitmap
 *
 * @author tian on 2019/12/26
 */
public class UrlToBitmapTestActivity extends AppCompatActivity {
    @InjectView(R.id.btnClick)
    Button btnClick;
    @InjectView(R.id.tvInfo)
    TextView tvInfo;
    @InjectView(R.id.img)
    ImageView img;

    String url = "http://b.hiphotos.baidu.com/zhidao/pic/item/060828381f30e924c9c50a6a4b086e061d95f70c.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_receiver_test);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.btnClick)
    public void onViewClicked() {
        initNetWorkImage(url,UrlToBitmapTestActivity.this);
    }

    /**
     * 网络加载图片的方法
     *
     * @param imgUrl 图片的网址
     */
    @SuppressLint("StaticFieldLeak")
    public void initNetWorkImage(final String imgUrl, final Activity context) {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... voids) {
                Bitmap bitmap = null;
                try {
                    bitmap = Glide.with(context)
                            .asBitmap()
                            .load(imgUrl)
                            .submit(360, 480)
                            .get();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                img.setImageBitmap(bitmap);
            }
        }.execute();
    }
}
