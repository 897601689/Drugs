package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.adapter.DrugsListAdapter;
import com.drugs.adapter.PlanListAdapter;
import com.drugs.bean.DrugsInfo;
import com.drugs.bean.DrugsUseInfo;
import com.drugs.dao.DBOperation;
import com.drugs.utils.Global;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends Activity {


    @BindView(R.id.txt_back)
    TextView txtBack;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.list_drugs)
    ListView listDrugs;

    TextView name;
    DBOperation db;//数据操作对象
    private List<DrugsInfo> list;
    private DrugsListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        db = new DBOperation(SearchActivity.this);
        list = new ArrayList<>();
        //实例化自定义内容适配类
        listAdapter = new DrugsListAdapter(SearchActivity.this, getLayoutInflater(), list);
        //为listView设置适配
        listDrugs.setAdapter(listAdapter);

        listDrugs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                name = view.findViewById(R.id.txt_drugs_name);
                Intent intent = new Intent(SearchActivity.this, DrugsActivity.class);
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

    @OnClick({R.id.txt_back, R.id.img_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.img_search:
                AddPatientData();
                break;
        }
    }
}
