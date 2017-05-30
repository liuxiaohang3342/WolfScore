package com.android.wolf.werewolfkillerscore.gamer;

import android.content.Intent;
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

public class GamerListActivity extends BaseActivity implements View.OnClickListener {

    private ListView mListView;

    private TextView mRightButton;

    private TextView mTitleView;

    private ArrayList<Gamer> mList;

    private GamerAdapter mAdaper;


    @Override
    protected View createTitleView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.title_right_button_layout, root, false);
        mTitleView = (TextView) view.findViewById(R.id.tv_title);
        mTitleView.setText(getResources().getString(R.string.main_gamer_list));
        mRightButton = (TextView) view.findViewById(R.id.tv_right_button);
        mRightButton.setText(getResources().getString(R.string.string_add));
        mRightButton.setOnClickListener(this);
        return view;
    }


    @Override
    protected View createContentView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.normal_listview_layout, root, false);
        mListView = (ListView) view.findViewById(R.id.lv_billboard);
        return view;
    }

    @Override
    protected void initAndSetData() {
        mList = GamerDataBaseManager.getInstance().queryAll();
        if (mList != null && mList.size() > 0) {
            mAdaper = new GamerAdapter(this, mList);
            mListView.setAdapter(mAdaper);
        } else {
            setNoDataTip(getResources().getString(R.string.no_gamer_tip));
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, CreateGamerActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Gamer gamer = data.getParcelableExtra("gamer");
            if (gamer == null) {
                return;
            }
            hideTip();
            if (mList == null) {
                mList = new ArrayList<>();
            }
            mList.add(gamer);
            if (mAdaper != null) {
                mAdaper.notifyDataSetChanged();
            } else {
                mAdaper = new GamerAdapter(this, mList);
                mListView.setAdapter(mAdaper);
            }
        }
    }
}
