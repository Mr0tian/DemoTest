package com.example.daotest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import java.sql.BatchUpdateException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/9/18
 */
public class PutActivity extends AppCompatActivity {


    @InjectView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        //创建需要对应目标Activity
        Intent intent = new Intent(this,ResultActivity.class);
        //启动指定Activity并等待返回的结果,其中0是请求码,用于标示该请求
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("data");
            tv.setText(result);


        }
    }
}
