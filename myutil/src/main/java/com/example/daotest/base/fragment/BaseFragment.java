package com.example.daotest.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.daotest.base.context.BaseContext;
import com.example.daotest.dialog.PermissionDialogFragment;
import com.example.daotest.permission.PermissionManager;

/**
 * @author tian on 2019/9/23
 * 公共Fragment
 */
public abstract class BaseFragment extends Fragment implements BaseContext {

    private static final String TAG = "BaseFragment";
    private PermissionDialogFragment settingDialog;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handleRequestPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onPermissionGranted(int requestCode) {

    }

    @Override
    public void onPermissionPermanentlyDenied(int requestCode) {
        if (settingDialog == null){
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
                            Toast.makeText(BaseFragment.this.getContext(), "请在应用管理开启权限", Toast.LENGTH_SHORT).show();
                        }
                    }).build();
        }
        settingDialog.show(this);
    }

    private void startForResult(int requestCode){
        this.startActivityForResult(
                new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        .setData(Uri.fromParts("package", getContext().getPackageName(), null)),
                requestCode);
    }


    @Override
    public void onPermissionDenied(int requestCode) {

    }
}
