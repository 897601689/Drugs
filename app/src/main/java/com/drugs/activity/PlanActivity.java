package com.drugs.activity;

import android.app.Activity;
import android.os.Bundle;

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
}
