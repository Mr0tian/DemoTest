package com.example.daotest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.daotest.R;
import com.example.daotest.wight.ProgressImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/12/31
 */
public class ProgressImageViewTestActivity extends AppCompatActivity {

    @InjectView(R.id.progress)
    ProgressImageView progressImageView;
    @InjectView(R.id.btn_click)
    Button btnClick;

    private float progress = 0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_image_view_test);
        ButterKnife.inject(this);

        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_" +
                        "10000&sec=1577770247533&di=94d6713d79eeaa4188264e6871c045b9&imgtype=0&src=h" +
                        "ttp%3A%2F%2Fdata.bbs.18183.com%2Fattachment%2Fforum%2F201611%2F04%2F144649oo" +
                        "sojrkfk0tsoke1.jpg")
                .into(progressImageView);


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progress >= 1) {
                progress = 1f;
                progressImageView.setProgress(progress);
            } else {
                progress += 0.01f;
                progressImageView.setProgress(progress);
                handler.sendEmptyMessageDelayed(123, 300);
            }
        }
    };

    public void startUpload() {
        handler.sendEmptyMessageDelayed(123, 300);
    }


    @OnClick(R.id.btn_click)
    public void onViewClicked() {
        progress = 0f;
        startUpload();
    }
}
