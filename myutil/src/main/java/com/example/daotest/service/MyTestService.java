package com.example.daotest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.view.KeyEventDispatcher;

/**
 * @author tian on 2019/9/19
 */
public class MyTestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }
}
