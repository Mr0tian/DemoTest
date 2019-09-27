package com.example.daotest.base.activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import com.example.daotest.base.context.BaseContext;

import com.example.daotest.base.fragment.BaseDialogFragment;
import com.example.daotest.dialog.PermissionDialogFragment;
import com.example.daotest.permission.PermissionManager;

/**
 * @author tian on 2019/9/23
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseContext {


    private PermissionDialogFragment settingDialog;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handleRequestPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Nullable
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onPermissionGranted(int requestCode) {

    }

    @Override
    public void onPermissionPermanentlyDenied(final int requestCode) {
        if (settingDialog == null) {
            settingDialog = new PermissionDialogFragment.Builder()
                    .setTitle("提示")
                    .setMessage("请打开设置开启应用权限")
                    .setPositiveText("设置")
                    .setCancelableType(BaseDialogFragment.NO_CANCELED)
                    .setOnDialogClickListener(new PermissionDialogFragment.OnDialogClickListener() {

                        @Override
                        public void onPositiveClick() {
                            startForResult(requestCode);
                        }

                        @Override
                        public void onNegativeClick() {
                            Toast.makeText(BaseActivity.this, "请在应用管理开启权限", Toast.LENGTH_SHORT).show();
                        }
                    }).build();
        }
        settingDialog.show(this);
    }

    private void startForResult(int requestCode) {
        this.startActivityForResult(
                new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        .setData(Uri.fromParts("package", getContext().getPackageName(), null)),
                requestCode);
    }

    @Override
    public void onPermissionDenied(int requestCode) {

    }

}
