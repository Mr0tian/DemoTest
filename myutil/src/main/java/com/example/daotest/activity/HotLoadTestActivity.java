package com.example.daotest.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/9/19
 */
public class HotLoadTestActivity extends AppCompatActivity {
    @InjectView(R.id.btn)
    Button btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinker);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        //apk下载管理
        /*"skdlj".substring(0,21);*/
        Toast.makeText(HotLoadTestActivity.this,"测试热更新",Toast.LENGTH_LONG).show();

    }
}
