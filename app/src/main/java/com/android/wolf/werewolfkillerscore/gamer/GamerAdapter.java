package com.android.wolf.werewolfkillerscore.gamer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.customview.SwipeLayout;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.sql.GamerDataBaseManager;
import com.android.wolf.werewolfkillerscore.sql.RoleDataBaseManager;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/28.
 */

public class GamerAdapter extends BaseAdapter {

    private ArrayList<Gamer> mList;

    private Context mContext;

    public GamerAdapter(Context context, ArrayList<Gamer> list) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.gamer_item_layout, viewGroup, false);
            viewHolder.nameView = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.descView = (TextView) view.findViewById(R.id.tv_desc);
            viewHolder.deleteView = view.findViewById(R.id.tv_delete);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (view instanceof SwipeLayout) {
            ((SwipeLayout) view).close();
        }
        final Gamer gamer = mList.get(i);
        viewHolder.nameView.setText(gamer.getName());
        viewHolder.descView.setText(gamer.getDesc());
        viewHolder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(gamer);
                notifyDataSetChanged();
                GamerDataBaseManager.getInstance().delete(gamer);
            }
        });
        return view;
    }

    class ViewHolder {
        TextView nameView;
        TextView descView;
        View deleteView;
    }

}
