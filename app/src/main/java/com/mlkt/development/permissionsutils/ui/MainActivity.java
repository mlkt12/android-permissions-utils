package com.mlkt.development.permissionsutils.ui;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import com.mlkt.development.permissionsutils.model.PermissionRequest;
import com.mlkt.development.permissionsutils.R;
import com.mlkt.development.permissionsutils.ui.base.BaseActivity;
import com.mlkt.development.permissionsutils.interfaces.PermissionCallback;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private static final String TAG = "PERMISSION_UTILS";
    private static final int LOCATION_PERMISSION_REQ_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission(new PermissionRequest(new ArrayList<String>() {{
            add(Manifest.permission.ACCESS_FINE_LOCATION);
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }}, LOCATION_PERMISSION_REQ_CODE), new PermissionCallback() {

            @Override
            public void onAllPermissionGranted() {
                Log.d(TAG,"onAllPermissionGranted");
            }

            @Override
            public void onPermissionGranted(String permission) {
                Log.d(TAG,"onPermissionGranted: "+permission);
            }

            @Override
            public void onPermissionDenied(String permission) {
                Log.d(TAG,"onPermissionDenied: "+permission);
            }

            @Override
            public void onPermissionError() {
                Log.d(TAG,"onPermissionError");
            }
        });
    }
}
