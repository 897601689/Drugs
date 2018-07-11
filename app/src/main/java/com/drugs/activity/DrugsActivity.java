package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.bean.DrugsInfo;
import com.drugs.dao.DBOperation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrugsActivity extends Activity {

    DBOperation db;//数据操作对象
    @BindView(R.id.txt_drugs_name)
    TextView txtDrugsName;
    @BindView(R.id.img_drugs)
    ImageView imgDrugs;
    @BindView(R.id.txt_drugs_address)
    TextView txtDrugsAddress;
    @BindView(R.id.key1)
    TextView key1;
    @BindView(R.id.key2)
    TextView key2;
    @BindView(R.id.key3)
    TextView key3;
    @BindView(R.id.otc)
    TextView otc;
    @BindView(R.id.otc_red)
    TextView otcRed;
    @BindView(R.id.normal)
    TextView normal;
    @BindView(R.id.bao)
    TextView bao;
    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.txt4)
    TextView txt4;
    @BindView(R.id.txt5)
    TextView txt5;
    @BindView(R.id.txt6)
    TextView txt6;
    @BindView(R.id.txt7)
    TextView txt7;
    @BindView(R.id.txt8)
    TextView txt8;
    @BindView(R.id.txt9)
    TextView txt9;
    @BindView(R.id.txt10)
    TextView txt10;
    @BindView(R.id.txt_drugs_back)
    TextView txtDrugsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        db = new DBOperation(DrugsActivity.this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name != null) {
            Cursor cursor = db.GetDrugsInfoByName(name);
            if (cursor.moveToNext()) {
                DrugsInfo drugsInfo = new DrugsInfo();
                drugsInfo.setDrugname(cursor.getString(1));                    //药品名称
                switch (cursor.getString(1)) {
                    case "双黄连口服液":
                        imgDrugs.setImageResource(R.mipmap.d1);
                        break;
                    case "复方双花片":
                        imgDrugs.setImageResource(R.mipmap.d2);
                        break;
                    case "维C银翘片":
                       imgDrugs.setImageResource(R.mipmap.d3);
                        break;
                    case "立效 参苓白术散":
                       imgDrugs.setImageResource(R.mipmap.d4);
                        break;
                    case "盐酸左氧氟沙星胶囊":
                        imgDrugs.setImageResource(R.mipmap.d5);
                        break;
                    default:
                        break;
                }
                drugsInfo.setDrugPrice(cursor.getString(2));                   //药品价格

                drugsInfo.setDescription(cursor.getString(3));                 //性状
                drugsInfo.setActions(cursor.getString(4));                     //药理作用
                drugsInfo.setIndications(cursor.getString(5));                 //适应症
                drugsInfo.setContraindications(cursor.getString(6));           //禁忌症
                drugsInfo.setDosageandadministration(cursor.getString(7));     //用量用法
                drugsInfo.setAdversereactions(cursor.getString(8));            //不良反应
                drugsInfo.setPrecautions(cursor.getString(9));                 //注意事项
                drugsInfo.setPackages(cursor.getString(10));                    //包装
                drugsInfo.setStorage(cursor.getString(11));                     //储藏

                drugsInfo.setOthers(cursor.getString(12));                      //其他

                txtDrugsName.setText(drugsInfo.getDrugname());
                key1.setText("￥" + drugsInfo.getDrugPrice());
                key1.setTextSize(18);
                key1.setTextColor(Color.rgb(251, 178, 65));

                txt1.setText(drugsInfo.getDrugname());
                txt2.setText(drugsInfo.getDescription());
                txt3.setText(drugsInfo.getActions());
                txt4.setText(drugsInfo.getIndications());
                txt5.setText(drugsInfo.getContraindications());
                txt6.setText(drugsInfo.getDosageandadministration());
                txt7.setText(drugsInfo.getAdversereactions());
                txt8.setText(drugsInfo.getPrecautions());
                txt9.setText(drugsInfo.getPackages());
                txt10.setText(drugsInfo.getStorage());

                if ("6933968000123".equals(drugsInfo.getOthers())) {
                    txtDrugsAddress.setText("哈药集团三精制药股份有限公司");
                } else {
                    txtDrugsAddress.setText(drugsInfo.getOthers());
                }
            }

            cursor.close();
        }
    }

    @OnClick(R.id.txt_drugs_back)
    public void onViewClicked() {
        finish();
    }
}
