package com.udayalakmal.androidlockscreendemo;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DeviceLockReceiver extends DeviceAdminReceiver {

    void showToast(Context context, String msg) {
        String status = msg;
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "Enabled admin privileges");
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "Disabled admin privileges";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        showToast(context, "Disabled admin privileges");
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {
        showToast(context, "Change password");
    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        showToast(context, "password succeeded");
    }

}
