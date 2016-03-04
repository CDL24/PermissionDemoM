package com.example.climbachiya.permissiondemom;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

/**
 * Created by C.limbachiya on 3/4/2016.
 */
public class MarshMallowPermission {

    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    Activity activity;
    CoordinatorLayout coordinatorLayout;

    public MarshMallowPermission(Activity activity, CoordinatorLayout coordinatorLayout) {
        this.activity = activity;
        this.coordinatorLayout = coordinatorLayout;
    }

    public boolean checkPermissionForExternalStorage(){
        int writeResult = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readResult = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (writeResult == PackageManager.PERMISSION_GRANTED && readResult == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForCamera(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public void requestPermissionForExternalStorage(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(activity, "External Storage permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "External Storage permission needed. Please allow in App Settings for additional functionality", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Setting", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            openSettingForPermission();
                        }
                    });
            snackbar.show();

        } else {

            ActivityCompat.requestPermissions
                    (activity, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
            //ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionForCamera(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)){
            Toast.makeText(activity, "Camera permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Camera permission needed. Please allow in App Settings for additional functionality", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Setting", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            openSettingForPermission();
                        }
                    });
            snackbar.show();

        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    //Open setting screen for app permission
    private void openSettingForPermission() {

        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }
}
