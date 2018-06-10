package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.adapter.PlanListAdapter;
import com.drugs.bean.DrugsUseInfo;
import com.drugs.dao.DBOperation;
import com.drugs.utils.Global;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用药记录表
 */
public class PlanActivity extends Activity {


    @BindView(R.id.txt_drugs_name)
    TextView txtDrugsName;
    @BindView(R.id.listView)
    ListView listView;

    TextView txt_title;
    DBOperation db;//数据操作对象
    private List<DrugsUseInfo> list;
    private PlanListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ButterKnife.bind(this);
        init();

    }

    protected void onResume() {

        super.onResume();
        AddPatientData();
    }
    private void init() {
        db = new DBOperation(PlanActivity.this);
        list = new ArrayList<>();
        //实例化自定义内容适配类
        listAdapter = new PlanListAdapter(PlanActivity.this, getLayoutInflater(), list);
        //为listView设置适配
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                txt_title = view.findViewById(R.id.txt_title);
                Intent intent = new Intent(PlanActivity.this, PlanDetailsActivity.class);
                intent.putExtra("name", txt_title.getText().toString());
                startActivity(intent);
            }
        });
    }

    /**
     * 添加数据
     */
    private void AddPatientData() {
        list.clear();
        Cursor cursor = db.GetPlanInfoByMid(Global.login_id);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                DrugsUseInfo drugsUseInfo = new DrugsUseInfo();
                drugsUseInfo.setTitle(cursor.getString(0));
                drugsUseInfo.setUseingtime(cursor.getString(1));
                list.add(drugsUseInfo);
            }
            listAdapter.notifyDataSetChanged();
            cursor.close();
        }

    }


    @OnClick(R.id.txt_drugs_name)
    public void onViewClicked() {
        if (!Global.login_state) {
            Intent intent = new Intent(PlanActivity.this, UserActivity.class);
            startActivity(intent);
        } else {
            AddPatientData();
        }

    }
}
