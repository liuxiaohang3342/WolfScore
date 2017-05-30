package com.android.wolf.werewolfkillerscore.billboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.base.BaseActivity;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.sql.GamerDataBaseManager;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/28.
 */

public class BillboardActivity extends BaseActivity {

    private ListView mListView;

    @Override
    protected View createContentView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.normal_listview_layout, root, false);
        mListView = (ListView) view.findViewById(R.id.lv_billboard);
        return view;
    }

    @Override
    protected void initAndSetData() {
        ArrayList<Gamer> gamers = GamerDataBaseManager.getInstance().queryAll("score desc");
        if (gamers != null && gamers.size() > 0) {
            addHeaderView();
            mListView.setAdapter(new BillboardAdapter(this, gamers));
        } else {
            setNoDataTip(getResources().getString(R.string.no_data_tip));
        }
    }

    private void addHeaderView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.billboard_item_layout, mListView, false);
        TextView numberView = (TextView) headerView.findViewById(R.id.tv_number);
        TextView nameView = (TextView) headerView.findViewById(R.id.tv_name);
        TextView scoreView = (TextView) headerView.findViewById(R.id.tv_score);
        TextView mvpView = (TextView) headerView.findViewById(R.id.tv_mvp);
        numberView.setText("名次");
        nameView.setText("玩家");
        scoreView.setText("积分");
        mvpView.setText("MVP");
        mListView.addHeaderView(headerView);
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.main_billboard);
    }
}
