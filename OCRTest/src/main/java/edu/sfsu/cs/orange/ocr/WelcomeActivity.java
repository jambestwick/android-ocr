package edu.sfsu.cs.orange.ocr;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import edu.sfsu.cs.orange.ocr.utils.PermissionHelper;

public class WelcomeActivity extends Activity {
    /********** is above android 6.0 request permission*******************************************************/
    static final int PERMISSION_REQUEST_CODE = 5;
    private final String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionHelper.checkPermission(this, permissions)) {
                startActivity(new Intent(this, CaptureActivity.class));
            } else {
                PermissionHelper.requestPermission(this, permissions, PERMISSION_REQUEST_CODE);
            }
        } else {
            startActivity(new Intent(this, CaptureActivity.class));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true;
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (isAllGranted) {
                startActivity(new Intent(this, CaptureActivity.class));

            } else {
                PermissionHelper.requestPermission(this, permissions, PERMISSION_REQUEST_CODE);
            }

        }
    }
}
