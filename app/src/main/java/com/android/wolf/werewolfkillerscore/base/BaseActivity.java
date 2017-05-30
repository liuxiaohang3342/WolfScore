package com.android.wolf.werewolfkillerscore.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.wolf.werewolfkillerscore.R;

/**
 * Created by lxh on 2017/5/28.
 */

public class BaseActivity extends FragmentActivity {

    protected TextView mTitleView;
    private TextView mTipView;
    private FrameLayout mContentLayout;
    private FrameLayout mTitleLayout;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        mTitleLayout = (FrameLayout) findViewById(R.id.fl_title);
        mContentLayout = (FrameLayout) findViewById(R.id.fl_content);
        mTitleView = (TextView) findViewById(R.id.tv_title);
        mTipView = (TextView) findViewById(R.id.tv_no_data_tip);
        mTitleView.setText(setTitle());
        View titleView = createTitleView(mTitleLayout);
        if (titleView != null) {
            mTitleLayout.removeAllViews();
            mTitleLayout.addView(titleView);
        }

        View contentView = createContentView(mContentLayout);
        if (contentView != null) {
            mContentLayout.addView(contentView);
        }
        initAndSetData();
    }

    protected String setTitle() {
        return "";
    }

    protected View createTitleView(ViewGroup root) {
        return null;
    }

    protected View createContentView(ViewGroup root) {
        return null;
    }

    protected void initAndSetData() {
    }

    protected void setNoDataTip(String tips) {
        mTipView.setVisibility(View.VISIBLE);
        mTipView.setText(tips);
    }

    protected void hideTip() {
        mTipView.setVisibility(View.GONE);
    }

}
