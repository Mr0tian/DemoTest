package com.example.daotest.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * @author tian on 2019/9/16
 */
public class SeekBarActivity extends AppCompatActivity {

    @InjectView(R.id.img)
    ImageView img;
    @InjectView(R.id.seekbar)
    SeekBar seekbar;
    @InjectView(R.id.seekbar2)
    SeekBar seekbar2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
        ButterKnife.inject(this);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //当拖动条的滑块位置发生改变时触发该方法

                //动态改变图片的透明度
                img.setImageAlpha(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
