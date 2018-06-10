package com.drugs.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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

/**
 * 就诊记录
 */
public class DrugUseActivity extends Activity {


    @BindView(R.id.txt_drugs_back)
    TextView txtDrugsBack;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.underline_layout)
    UnderLineLinearLayout underlineLayout;

    DBOperation db;//数据操作对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        db = new DBOperation(DrugUseActivity.this);

        Cursor cursor = db.GetVisitInfoByid(Global.login_id);
        while (cursor.moveToNext()) {
            View v = LayoutInflater.from(this).inflate(R.layout.item_vertical, underlineLayout, false);
            ((TextView) v.findViewById(R.id.tx_action)).setText(cursor.getString(4));
            ((TextView) v.findViewById(R.id.tx_action_time)).setText(cursor.getString(3));
            ((TextView) v.findViewById(R.id.tx_action_status)).setText("完成");
            underlineLayout.addView(v);
        }

    }

    @OnClick(R.id.txt_drugs_back)
    public void onViewClicked() {
        finish();
    }
}
