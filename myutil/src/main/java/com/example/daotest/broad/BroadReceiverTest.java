package com.example.daotest.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/12/24
 */
public class BroadReceiverTest extends AppCompatActivity {


    @InjectView(R.id.btnClick)
    Button btnClick;
    @InjectView(R.id.tvInfo)
    TextView tvInfo;
    private MyReceiver myReceiver;
    private IntentFilter filter;
    private Context mContext;

    private static final String MY_BROADCAST_TAG = "com.example.daotest.broad";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_receiver_test);
        ButterKnife.inject(this);
        SharedPreferences sharedPreferences = this.getSharedPreferences("TEST",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("test","这个是测试数据");
        editor.apply();
        myReceiver = new MyReceiver();
        filter = new IntentFilter();
        filter.addAction(MY_BROADCAST_TAG);


    }

    @OnClick(R.id.btnClick)
    public void onViewClicked() {

        Intent intent = new Intent();
        intent.setAction(MY_BROADCAST_TAG);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
    }




}
