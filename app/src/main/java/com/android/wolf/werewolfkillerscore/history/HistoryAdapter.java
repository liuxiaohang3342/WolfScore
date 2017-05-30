package com.android.wolf.werewolfkillerscore.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.customview.SwipeLayout;
import com.android.wolf.werewolfkillerscore.interfaces.Constant;
import com.android.wolf.werewolfkillerscore.models.Game;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.models.GamerRecord;
import com.android.wolf.werewolfkillerscore.models.Role;
import com.android.wolf.werewolfkillerscore.sql.GameHistoryDataBaseManager;
import com.android.wolf.werewolfkillerscore.sql.GamerDataBaseManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lxh on 2017/5/29.
 */

public class HistoryAdapter extends BaseAdapter {
    private ArrayList<Game> mGames;
    private Context mContext;

    public HistoryAdapter(Context context, ArrayList<Game> games) {
        mGames = games;
        mContext = context;

    }

    @Override
    public int getCount() {
        return mGames.size();
    }

    @Override
    public Object getItem(int i) {
        return mGames.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.history_item_layout, viewGroup, false);
        }
        TextView timeView = (TextView) view.findViewById(R.id.tv_time);
        TextView winCampView = (TextView) view.findViewById(R.id.win_camp);
        TextView contentView = (TextView) view.findViewById(R.id.tv_game_history);
        TextView tvMvpView = (TextView) view.findViewById(R.id.tv_mvp);
        TextView tvDeleteView = (TextView) view.findViewById(R.id.tv_delete);
        if (view instanceof SwipeLayout) {
            ((SwipeLayout) view).close();
        }
        final Game game = mGames.get(i);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(game.getTime());
        Date date = c.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeView.setText(format.format(date));
        if (game.getWin() == Constant.WOLF_WIN) {
            winCampView.setText(mContext.getResources().getString(R.string.wolf_win));
        } else {
            winCampView.setText(mContext.getResources().getString(R.string.good_win));
        }
        Gamer mvp = GamerDataBaseManager.getInstance().query(String.valueOf(game.getMvp()));
        if (mvp != null) {
            tvMvpView.setText("MVP:" + mvp.getName());
        }
        StringBuilder content = new StringBuilder();
        ArrayList<GamerRecord> records = game.getmGamerToRole();
        for (GamerRecord record : records) {
            if (record != null) {
                Gamer gamer = record.getGamer();
                if (gamer != null) {
                    content.append(gamer.getName()).append(":");
                } else {
                    content.append("黑名单").append(":");
                }
                Role role = record.getRole();
                if (role != null) {
                    content.append(role.getName()).append("  ");
                } else {
                    content.append("已删除").append("  ");
                }
            }
        }
        contentView.setText(content.toString());
        tvDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGames.remove(game);
                notifyDataSetChanged();
                GameHistoryDataBaseManager.getInstance().delete(String.valueOf(game.getId()));
            }
        });
        return view;
    }
}
