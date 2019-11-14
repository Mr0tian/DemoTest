package com.example.daotest.activity;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;


/**
 * @author tian on 2019/10/9
 */
public class ResourceActivity extends AppCompatActivity {

    /**
     * 获取res资源的方法
     */
    Resources resource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resource = this.getResources();
        setContentView(R.layout.activity_resources);


    }
}
