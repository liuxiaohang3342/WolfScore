package com.android.wolf.werewolfkillerscore.creategame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.sql.GamerDataBaseManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lxh on 2017/5/28.
 */

public class ChooseGamerFragment extends Fragment {

    private GridView mGridView;

    private ArrayList<Gamer> mGamers;

    private ChooseGamerAdapter mAdaper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGamers = GamerDataBaseManager.getInstance().queryAll();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.choose_gamer_layout, container, false);
        mGridView = (GridView) view.findViewById(R.id.gv_choose_gamer);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mGamers != null && mGamers.size() > 0) {
            mAdaper = new ChooseGamerAdapter(getActivity(), mGamers);
            mGridView.setAdapter(mAdaper);
        }
    }

    public ArrayList<Gamer> getGamers() {
        return mGamers;
    }

    public void chooseComplete() {
        Iterator<Gamer> iterator = mGamers.iterator();
        while (iterator.hasNext()) {
            Gamer gamer = iterator.next();
            if (gamer.getRole() == null) {
                iterator.remove();
            }
        }
        mAdaper.setClickable(false);
        mAdaper.notifyDataSetChanged();
    }


}

