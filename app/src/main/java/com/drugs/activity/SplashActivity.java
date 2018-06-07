package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.drugs.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent it = new Intent();
                it.setClass(SplashActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        }, 100);
    }

}
