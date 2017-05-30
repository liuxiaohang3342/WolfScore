package com.android.wolf.werewolfkillerscore.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.base.BaseActivity;
import com.android.wolf.werewolfkillerscore.models.Game;
import com.android.wolf.werewolfkillerscore.sql.GameHistoryDataBaseManager;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/28.
 */

public class HistoryActivity extends BaseActivity {

    private ListView mListView;

    @Override
    protected View createContentView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.normal_listview_layout, root, false);
        mListView = (ListView) view.findViewById(R.id.lv_billboard);
        return view;
    }

    @Override
    protected void initAndSetData() {
        ArrayList<Game> games = GameHistoryDataBaseManager.getInstance().queryAll();
        if (games != null && games.size() > 0) {
            mListView.setAdapter(new HistoryAdapter(this, games));
        } else {
            setNoDataTip(getResources().getString(R.string.no_data_tip));
        }

    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.main_history_record);
    }
}
