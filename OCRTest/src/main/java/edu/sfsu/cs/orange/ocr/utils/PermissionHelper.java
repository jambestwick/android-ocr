package edu.sfsu.cs.orange.ocr.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

public class PermissionHelper {
    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermission(Activity context, String[] permissions, int requestCode) {
        for (String permission : permissions) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                context.requestPermissions(new String[]{permission}, requestCode);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkPermission(Activity context, String[] permissions) {
        for (String permission : permissions) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
