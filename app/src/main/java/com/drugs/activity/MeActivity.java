package com.drugs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.drugs.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivity extends Activity {
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.txt_visit)
    LinearLayout txtVisit;
    @BindView(R.id.txt_drug_record)
    LinearLayout txtDrugRecord;
    @BindView(R.id.txt_pay_record)
    LinearLayout txtPayRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
    }

    @OnClick({R.id.txt_login, R.id.txt_visit, R.id.txt_drug_record, R.id.txt_pay_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_login:
                break;
            case R.id.txt_visit:
                break;
            case R.id.txt_drug_record:
                break;
            case R.id.txt_pay_record:
                break;
        }
    }
}
