package com.example.daotest.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import io.alterac.blurkit.BlurLayout;

/**
 * @author tian on 2019/12/19
 */
public class BlurTestActivity extends AppCompatActivity {

    BlurLayout blurLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
        blurLayout = findViewById(R.id.blurLayout);


    }

    @Override
    protected void onStart() {
        super.onStart();
        blurLayout.startBlur();

    }

    @Override
    protected void onStop() {
        blurLayout.pauseBlur();
        super.onStop();
    }
}
