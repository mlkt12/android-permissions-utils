package com.mlkt.development.permissionsutils.interfaces;

public interface PermissionCallback{
    void onAllPermissionGranted();
    void onPermissionGranted(String permission);
    void onPermissionDenied(String permission);
    void onPermissionError();
}


