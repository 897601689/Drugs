package com.drugs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.drugs.R;

import butterknife.ButterKnife;

public class PlanActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
    }

    protected void onResume() {

        super.onResume();
        Log.e("TAG", "onResume: " );
    }
    protected void onPause() {

        super.onPause();
        Log.e("TAG", "onPause: " );
    }
    protected void onStart() {

        super.onStart();
        Log.e("TAG", "onStart: " );
    }
    protected void onStop() {

        super.onStop();
        Log.e("TAG", "onStop: " );
    }
}
