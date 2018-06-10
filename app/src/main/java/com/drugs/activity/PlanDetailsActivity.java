package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.dao.DBOperation;
import com.drugs.utils.Global;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import razerdp.widget.UnderLineLinearLayout;

public class PlanDetailsActivity extends Activity {


    @BindView(R.id.txt_back)
    TextView txtBack;
    @BindView(R.id.txt_drugs_name)
    TextView txtDrugsName;
    @BindView(R.id.underline_layout)
    UnderLineLinearLayout underlineLayout;

    DBOperation db;//数据操作对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_details);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        db = new DBOperation(PlanDetailsActivity.this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name != null) {
            Cursor cursor = db.GetPlanDetailsInfoByMid(Global.login_id, name);
            while (cursor.moveToNext()) {
                View v = LayoutInflater.from(this).inflate(R.layout.item_vertical, underlineLayout, false);
                ((TextView) v.findViewById(R.id.tx_action)).setText(cursor.getString(4)+ " \n用法用量：一日"+cursor.getString(5)+"次");
                ((TextView) v.findViewById(R.id.tx_action_time)).setText(cursor.getString(6));
                ((TextView) v.findViewById(R.id.tx_action_status)).setText("已服用");
                underlineLayout.addView(v);
            }
        }

    }

    @OnClick(R.id.txt_back)
    public void onViewClicked() {
        finish();
    }
}
