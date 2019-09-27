package com.example.daotest.permission;

/**
 * @author tian on 2019/9/20
 */
public interface PermissionListener  {

    void onPermissionGranted(int requestCode);

    void onPermissionPermanentlyDenied(int requestCode);

    void onPermissionDenied(int requestCode);
}
