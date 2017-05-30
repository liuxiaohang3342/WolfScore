package com.android.wolf.werewolfkillerscore.creategame;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.base.BaseActivity;
import com.android.wolf.werewolfkillerscore.models.Game;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.models.GamerRecord;
import com.android.wolf.werewolfkillerscore.models.Role;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/28.
 */

public class CreateGameActivity extends BaseActivity implements View.OnClickListener {

    private TextView mRightButton;

    private TextView mTitleView;

    private ChooseGamerFragment mFragment;

    private Game mGame = new Game();

    private boolean isCreated;

    @Override
    protected View createTitleView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.title_right_button_layout, root, false);
        mTitleView = (TextView) view.findViewById(R.id.tv_title);
        mTitleView.setText(getResources().getString(R.string.main_create_game));
        mRightButton = (TextView) view.findViewById(R.id.tv_right_button);
        mRightButton.setText(getResources().getString(R.string.create_new));
        mRightButton.setOnClickListener(this);
        return view;
    }

    @Override
    protected View createContentView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.create_game_layout, root, false);
        return view;
    }

    @Override
    protected void initAndSetData() {
        super.initAndSetData();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mFragment = new ChooseGamerFragment();
        transaction.add(R.id.fl_content, mFragment, ChooseGamerFragment.class.getName());
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.main_create_game);
    }

    @Override
    public void onClick(View view) {
        if (isCreated) {
            mTitleView.setText("游戏结算");
            mRightButton.setVisibility(View.GONE);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(mFragment);
            GameOverFragment fragment = new GameOverFragment();
            fragment.mGame = mGame;
            transaction.add(R.id.fl_content, fragment, GameOverFragment.class.getName());
            transaction.commitAllowingStateLoss();
        } else {
            ArrayList<Gamer> gamers = mFragment.getGamers();
            for (Gamer gamer : gamers) {
                Role role = gamer.getRole();
                if (role != null) {
                    GamerRecord record = new GamerRecord();
                    record.setGamer(gamer);
                    record.setRole(role);
                    mGame.addGameRecord(record);
                }
            }
            if (mGame.getmGamerToRole().size() <= 0) {
                Toast.makeText(this, "请为玩家添加角色", Toast.LENGTH_LONG).show();
                return;
            }
            mTitleView.setText("游戏中");
            isCreated = true;
            mRightButton.setText("结束");
            mFragment.chooseComplete();
        }
    }
}
