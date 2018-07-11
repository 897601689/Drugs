package com.drugs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.activity.HomeActivity;
import com.drugs.bean.DrugsInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrugsListAdapter2 extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<DrugsInfo> list;

    public DrugsListAdapter2(HomeActivity searchActivity, LayoutInflater layoutInflater, List<DrugsInfo> list) {
        this.mContext = searchActivity;
        this.inflater = layoutInflater;
        this.list = list;
    }

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
            convertView = inflater.inflate(R.layout.item_drugs, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtDrugsName.setText(list.get(position).getDrugname());
        holder.txtDrugsPrice.setText(list.get(position).getDrugPrice());
        holder.otc.setVisibility(View.VISIBLE);
        switch (list.get(position).getDrugname()) {
            case "双黄连口服液":
                holder.imgDrugs.setImageResource(R.mipmap.d1);
                break;
            case "复方双花片":
                holder.imgDrugs.setImageResource(R.mipmap.d2);
                break;
            case "维C银翘片":
                holder.imgDrugs.setImageResource(R.mipmap.d3);
                break;
            case "立效 参苓白术散":
                holder.imgDrugs.setImageResource(R.mipmap.d4);
                break;
            case "盐酸左氧氟沙星胶囊":
                holder.imgDrugs.setImageResource(R.mipmap.d5);
                break;
            default:
                break;
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.img_drugs)
        ImageView imgDrugs;
        @BindView(R.id.txt_drugs_name)
        TextView txtDrugsName;
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
