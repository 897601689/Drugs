package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.adapter.DrugsListAdapter2;
import com.drugs.bean.DrugsInfo;
import com.drugs.dao.DBOperation;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends Activity {
    @BindView(R.id.edit_search)
    TextView editSearch;
    @BindView(R.id.img_scan)
    ImageView imgScan;
    @BindView(R.id.gridView)
    GridView gridView;

    private int REQUEST_CODE_SCAN = 111;


    TextView name;
    DBOperation db;//数据操作对象
    private List<DrugsInfo> list;
    private DrugsListAdapter2 listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
        AddPatientData();
    }

    private void init() {
        db = new DBOperation(HomeActivity.this);
        list = new ArrayList<>();
        //实例化自定义内容适配类
        listAdapter = new DrugsListAdapter2(HomeActivity.this, getLayoutInflater(), list);
        //为listView设置适配
        gridView.setAdapter(listAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                name = view.findViewById(R.id.txt_drugs_name);
                Intent intent = new Intent(HomeActivity.this, DrugsActivity.class);
                intent.putExtra("name", name.getText().toString());
                startActivity(intent);
            }
        });
    }

    /**
     * 添加数据
     */
    private void AddPatientData() {

        Cursor cursor = db.GetDrugsInfoByName(editSearch.getText().toString());
        if (cursor != null) {
            while (cursor.moveToNext()) {
                DrugsInfo drugsInfo = new DrugsInfo();
                drugsInfo.setDrugname(cursor.getString(1));
                drugsInfo.setDrugPrice("￥"+cursor.getString(2));
                list.add(drugsInfo);
            }
            listAdapter.notifyDataSetChanged();
            cursor.close();
        }

    }



    @OnClick({R.id.edit_search, R.id.img_scan})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.edit_search:
                intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.img_scan:
                intent = new Intent(HomeActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
//                result.setText("扫描结果为：" + content);
                Log.e("TAG", "onActivityResult: " + content);
                if ("6933968000123".equals(content)) {
                    Intent intent = new Intent(HomeActivity.this, DrugsActivity.class);
                    intent.putExtra("name", "双黄连口服液");
                    startActivity(intent);
                }
            }
        }
    }
}
