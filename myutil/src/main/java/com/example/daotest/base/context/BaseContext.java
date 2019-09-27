package com.example.daotest.base.context;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.daotest.permission.PermissionListener;

/**
 * @author tian on 2019/9/20
 */
public interface BaseContext extends PermissionListener {

    @Nullable
    Context getContext();
}
