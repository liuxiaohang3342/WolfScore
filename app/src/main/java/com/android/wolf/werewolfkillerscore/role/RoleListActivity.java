package com.android.wolf.werewolfkillerscore.role;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.base.BaseActivity;
import com.android.wolf.werewolfkillerscore.models.Role;
import com.android.wolf.werewolfkillerscore.sql.RoleDataBaseManager;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/28.
 */

public class RoleListActivity extends BaseActivity implements View.OnClickListener {

    private ListView mListView;

    private ArrayList<Role> mListData;

    private TextView mRightButton;

    private TextView mTitleView;

    private RoleAdapter mAdaper;

    @Override
    protected View createTitleView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.title_right_button_layout, root, false);
        mTitleView = (TextView) view.findViewById(R.id.tv_title);
        mTitleView.setText(getResources().getString(R.string.main_role_list));
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
        mListData = RoleDataBaseManager.getInstance().queryAll();
        if (mListData != null && mListData.size() > 0) {
            mAdaper = new RoleAdapter(this, mListData);
            mListView.setAdapter(mAdaper);
        } else {
            setNoDataTip(getResources().getString(R.string.no_data_tip));
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, CreateRoleActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Role role = data.getParcelableExtra("role");
            if (role == null) {
                return;
            }
            hideTip();
            if (mListData == null) {
                mListData = new ArrayList<>();
            }
            mListData.add(role);
            if (mAdaper != null) {
                mAdaper.notifyDataSetChanged();
            } else {
                mAdaper = new RoleAdapter(this, mListData);
                mListView.setAdapter(mAdaper);
            }
        }
    }
}
