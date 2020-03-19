package com.example.daotest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;
import com.example.daotest.wight.YWaveLoadView;

/**
 * @author tian on 2019/12/30
 */
public class WaveLoadTestActivity extends AppCompatActivity {

    private YWaveLoadView mLoadView1;
    private YWaveLoadView mLoadView2;
    private YWaveLoadView mLoadView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_load);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("YWaveLoadView");
        }

        mLoadView1 = findViewById(R.id.wave_load_1);
        mLoadView2 = findViewById(R.id.wave_load_2);
        mLoadView3 = findViewById(R.id.wave_load_3);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadView2.startLoad();
            }
        }, 1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadView3.startLoad();
            }
        }, 2000);
    }
}
