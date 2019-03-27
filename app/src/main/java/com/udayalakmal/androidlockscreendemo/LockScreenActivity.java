package com.udayalakmal.androidlockscreendemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class LockScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

    }

    long userInteractionTime = 0;

    @Override
    public void onUserInteraction() {
        userInteractionTime = System.currentTimeMillis();
        super.onUserInteraction();
        Log.i("appname","Interaction");
    }

    @Override
    public void onUserLeaveHint() {
        long uiDelta = (System.currentTimeMillis() - userInteractionTime);

        super.onUserLeaveHint();
        if (uiDelta < 100)
            Log.i("appname","Home Key Pressed");
        else
            Log.i("appname","We are leaving, but will probably be back shortly!");
    }
}
