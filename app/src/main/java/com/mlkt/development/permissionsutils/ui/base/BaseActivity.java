package com.mlkt.development.permissionsutils.ui.base;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.mlkt.development.permissionsutils.model.PermissionRequest;
import com.mlkt.development.permissionsutils.interfaces.PermissionCallback;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    private PermissionCallback permissionListener;
    private List<PermissionRequest> permissionMap = new ArrayList<>();

    public void requestPermission(PermissionRequest permission, PermissionCallback listener) {
        permissionMap.add(permission);
        this.permissionListener = listener;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission.permission.toArray(new String[permission.permission.size()]), permission.requestCode);
        } else {
            permissionListener.onAllPermissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (permissionListener != null) {
            if (permissions.length != grantResults.length) {
                permissionListener.onPermissionError();
            } else {

                if (!intArrayContains(grantResults,-1)) {
                    permissionListener.onAllPermissionGranted();
                } else {

                    for (PermissionRequest p : permissionMap) {
                        if (p.requestCode == requestCode) {
                            for (int i = 0; i < permissions.length; i++) {

                                if (grantResults[i] == -1) {
                                    permissionListener.onPermissionDenied(permissions[i]);
                                } else {
                                    permissionListener.onPermissionGranted(permissions[i]);
                                }

                            }

                        }

                    }

                }
                permissionMap.clear();
            }
        }
    }


    private boolean intArrayContains(int[] intArray,int value){
        boolean result = false;
        for (int i=0;i<intArray.length;i++){
            if(intArray[i]==value){
                result = true;
                break;
            }
        }
        return result;
    }
}
