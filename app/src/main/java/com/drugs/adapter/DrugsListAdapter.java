package com.drugs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.bean.DrugsInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrugsListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<DrugsInfo> list;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_drugs2, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.imgDrugs.setImageDrawable(list.get());
        return null;
    }

    static class ViewHolder {
        @BindView(R.id.img_drugs)
        ImageView imgDrugs;
        @BindView(R.id.txt_drugs_name)
        TextView txtDrugsName;
        @BindView(R.id.txt_drugs_info)
        TextView txtDrugsInfo;
        @BindView(R.id.txt_drugs_price)
        TextView txtDrugsPrice;
        @BindView(R.id.otc)
        TextView otc;
        @BindView(R.id.otc_red)
        TextView otcRed;
        @BindView(R.id.normal)
        TextView normal;
        @BindView(R.id.bao)
        TextView bao;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
