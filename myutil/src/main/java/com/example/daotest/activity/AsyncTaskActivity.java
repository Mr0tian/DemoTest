package com.example.daotest.activity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/9/17
 */
public class AsyncTaskActivity extends AppCompatActivity {

    @InjectView(R.id.show)
    TextView show;
    @InjectView(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
    }
}
