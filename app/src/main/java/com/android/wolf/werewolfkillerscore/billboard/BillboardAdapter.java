package com.android.wolf.werewolfkillerscore.billboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.models.Gamer;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/29.
 */

public class BillboardAdapter extends BaseAdapter {
    private ArrayList<Gamer> mList;

    private Context mContext;

    public BillboardAdapter(Context context, ArrayList<Gamer> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.billboard_item_layout, viewGroup, false);
            viewHolder.numberView = (TextView) view.findViewById(R.id.tv_number);
            viewHolder.nameView = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.scoreView = (TextView) view.findViewById(R.id.tv_score);
            viewHolder.mvpView = (TextView) view.findViewById(R.id.tv_mvp);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Gamer gamer = mList.get(i);
        viewHolder.numberView.setText(String.valueOf(i + 1));
        viewHolder.nameView.setText(gamer.getName());
        viewHolder.scoreView.setText(String.valueOf(gamer.getScore()) + "分");
        viewHolder.mvpView.setText(String.valueOf(gamer.getMvp()) + "次");
        return view;
    }

    class ViewHolder {
        TextView nameView;
        TextView scoreView;
        TextView numberView;
        TextView mvpView;
    }
}
