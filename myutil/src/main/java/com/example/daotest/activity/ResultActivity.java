package com.example.daotest.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * @author tian on 2019/9/18
 */
public class ResultActivity extends AppCompatActivity {

    @InjectView(R.id.edit)
    EditText edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.inject(this);

        //获取该Activity对应的Intent的Component属性
        ComponentName comp = getIntent().getComponent();
        edit.setText("组件包名为:" + comp.getPackageName() + "\n 组件类名为:"+ comp.getClassName());
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {

        Intent intent = getIntent();
        intent.putExtra("data",edit.getText().toString());
        setResult(0,intent);
        finish();
    }
}
