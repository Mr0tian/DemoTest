package com.example.daotest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

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

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {

        Intent intent = getIntent();
        intent.putExtra("data",edit.getText().toString());
        setResult(0,intent);
        finish();

    }
}
