package com.example.daotest.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import com.example.daotest.R;

/**
 * @author tian on 2019/9/19
 */
public class IntentTestActivity extends AppCompatActivity {

    public static final String CRAZYIT_ACTION = "com.example.intent.action.CRAZYIT_ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
      /*  ComponentName comp = new ComponentName(
                this,ResultActivity.class);
        Intent intent = new Intent();
        intent.setComponent(comp);
        startActivity(intent);*/

      Intent intent = new Intent();
      intent.setAction(CRAZYIT_ACTION);
      startActivity(intent);

    }
}
