package com.drugs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.activity.PlanActivity;
import com.drugs.bean.DrugsUseInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<DrugsUseInfo> list;

    public PlanListAdapter(PlanActivity planActivity, LayoutInflater layoutInflater, List<DrugsUseInfo> list) {
        this.mContext = planActivity;
        this.inflater = layoutInflater;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_plan, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtTitle.setText(list.get(i).getTitle());
        holder.txtAction.setText(list.get(i).getUseingtime());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_action)
        TextView txtAction;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
