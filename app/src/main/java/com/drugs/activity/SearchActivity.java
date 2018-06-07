package com.drugs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.drugs.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends Activity {
    @BindView(R.id.txt_back)
    TextView txtBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.list_drugs)
    ListView listDrugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        init();

    }

    private void init() {

    }

    @OnClick({R.id.txt_back, R.id.img_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.img_search:

                break;
        }
    }
}
