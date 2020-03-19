package com.example.daotest.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * @author tian on 2019/12/25
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //message received
        Log.i("TAG","接收到信息");
        SharedPreferences sharedPreferences = context.getSharedPreferences("TEST",Context.MODE_PRIVATE);

        if (sharedPreferences.contains("test")){
            Log.i("TAG","接收到信息++"+sharedPreferences.getString("test","无"));
        }

    }
}