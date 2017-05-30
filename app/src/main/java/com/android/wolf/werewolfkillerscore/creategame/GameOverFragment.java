package com.android.wolf.werewolfkillerscore.creategame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.interfaces.Constant;
import com.android.wolf.werewolfkillerscore.models.Game;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.models.GamerRecord;
import com.android.wolf.werewolfkillerscore.models.Role;
import com.android.wolf.werewolfkillerscore.sql.GameHistoryDataBaseManager;
import com.android.wolf.werewolfkillerscore.sql.GamerDataBaseManager;
import com.android.wolf.werewolfkillerscore.sql.GamerRecordDataBaseManager;

import java.util.ArrayList;

import static com.android.wolf.werewolfkillerscore.interfaces.Constant.GOD_WIN;
import static com.android.wolf.werewolfkillerscore.interfaces.Constant.WOLF_WIN;

/**
 * Created by lxh on 2017/5/29.
 */

public class GameOverFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public Game mGame;
    private RadioGroup mRadioGroup;
    private Spinner mSpinner;

    private ArrayList<GamerRecord> mRecords;

    private Gamer mMvpGamer;
    private LinearLayout mAddScoreContainer;
    private LinearLayout mReduceScoreContainer;

    private View mAddScoreTip;
    private View mReduceScoreTip;
    private View mAddSplitView;
    private View mReduceSplitView;

    private ArrayList<GamerRecord> mRoles = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_over_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg_camp);
        mSpinner = (Spinner) view.findViewById(R.id.wv_choose_mvp);
        mAddScoreContainer = (LinearLayout) view.findViewById(R.id.ll_add_score);
        mReduceScoreContainer = (LinearLayout) view.findViewById(R.id.ll_reduce_score);
        mAddScoreTip = view.findViewById(R.id.tv_add_score);
        mAddSplitView = view.findViewById(R.id.view_add_splite);
        mReduceScoreTip = view.findViewById(R.id.tv_reduce_score);
        mReduceSplitView = view.findViewById(R.id.view_reduce_splite);
        view.findViewById(R.id.tv_confirm).setOnClickListener(this);
        mRecords = mGame.getmGamerToRole();
        initWheelView();
        initScoreView();
    }

    private void initScoreView() {
        for (GamerRecord record : mRecords) {
            if (record.getRole().getCamp() == 1) {
                mRoles.add(record);
            }
        }
        if (mRoles.size() == 0) {
            mAddScoreTip.setVisibility(View.GONE);
            mAddScoreContainer.setVisibility(View.GONE);
            mReduceScoreTip.setVisibility(View.GONE);
            mReduceScoreContainer.setVisibility(View.GONE);
            mAddSplitView.setVisibility(View.GONE);
            mReduceSplitView.setVisibility(View.GONE);
            return;
        }
        for (GamerRecord record : mRoles) {
            mAddScoreContainer.addView(createCheckBox(record));
            mReduceScoreContainer.addView(createCheckBox(record));
        }
    }

    private View createCheckBox(GamerRecord record) {
        CheckBox c = new CheckBox(getActivity());
        c.setTag(record.getId());
        c.setText(record.getRole().getName());
        return c;
    }

    private void initWheelView() {
        ArrayList<String> items = new ArrayList<>();
        final ArrayList<GamerRecord> records = mGame.getmGamerToRole();
        for (GamerRecord record : records) {
            items.add(record.getGamer().getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        mGame.setTime(System.currentTimeMillis());
        long gid = GameHistoryDataBaseManager.getInstance().insert(mGame);
        mGame.setId(gid);
        accountWinScore();
        accountMvpScore();
        accountAddReduceScore();
        getActivity().finish();
    }

    private void accountAddReduceScore() {
        int count = mAddScoreContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            CheckBox addCheck = (CheckBox) mAddScoreContainer.getChildAt(i);
            CheckBox reduceCheck = (CheckBox) mReduceScoreContainer.getChildAt(i);
            if (addCheck.isChecked()) {
                Gamer gamer = mRoles.get(i).getGamer();
                gamer.addScore(Constant.GOD_SKILL_SCORE);
                GamerDataBaseManager.getInstance().update(gamer);
            } else if (reduceCheck.isChecked()) {
                Gamer gamer = mRoles.get(i).getGamer();
                gamer.reduceScore(Constant.GOD_SKILL_SCORE);
                GamerDataBaseManager.getInstance().update(gamer);
            }
        }
    }

    private void accountMvpScore() {
        mMvpGamer.addMvp();
        mMvpGamer.addScore(Constant.MVP_SCORE);
        GamerDataBaseManager.getInstance().update(mMvpGamer);
    }

    private void accountWinScore() {
        if (mRadioGroup.getCheckedRadioButtonId() == R.id.rb_good_camp) {
            mGame.setWin(GOD_WIN);
            for (GamerRecord record : mRecords) {
                Role role = record.getRole();
                if (role != null && role.getCamp() != 2) {
                    record.setScore(role.getScore());
                    Gamer gamer = record.getGamer();
                    gamer.addScore(role.getScore());
                    GamerDataBaseManager.getInstance().update(gamer);
                }
                record.setGid(mGame.getId());
                long id = GamerRecordDataBaseManager.getInstance().insert(record);
                record.setId(id);
            }
        } else {
            mGame.setWin(WOLF_WIN);
            for (GamerRecord record : mRecords) {
                Role role = record.getRole();
                if (role != null && role.getCamp() == 2 || "法官".equals(role.getName())) {
                    record.setScore(2);
                    Gamer gamer = record.getGamer();
                    gamer.addScore(role.getScore());
                    GamerDataBaseManager.getInstance().update(gamer);
                }
                record.setGid(mGame.getId());
                long id = GamerRecordDataBaseManager.getInstance().insert(record);
                record.setId(id);
            }
        }
        GameHistoryDataBaseManager.getInstance().update(mGame);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mMvpGamer = mRecords.get(i).getGamer();
        mGame.setMvp(mMvpGamer.getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
