package com.udayalakmal.androidlockscreendemo;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    static DevicePolicyManager devicePolicyManager;
    static ComponentName mAdminName;
    static final int ACTIVATION_REQUEST = 47;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devicePolicyManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        mAdminName = new ComponentName(this, DeviceLockReceiver.class);



        if(!devicePolicyManager.isAdminActive(mAdminName)) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"Explanation!!");
            startActivityForResult(intent, ACTIVATION_REQUEST);
        }


        Button btn  = findViewById(R.id.btnDemo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(devicePolicyManager.isAdminActive(mAdminName)) {
                    devicePolicyManager.lockNow();
                }



            }
        });

        PhoneUnlockedReceiver receiver = new PhoneUnlockedReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, filter);


    }

}

 class PhoneUnlockedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);

        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            if (keyguardManager.isKeyguardSecure()) {
                Log.d("Test", "Device unlocked");
                //phone was unlocked, do stuff here
                Intent i = new Intent(context, UnlockActivity.class);
                context.startActivity(i);
            }
        }else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            Log.d("Test", "Device screen off");

                    Intent i = new Intent(context, LockScreenActivity.class);
                    context.startActivity(i);


        }

    }
}