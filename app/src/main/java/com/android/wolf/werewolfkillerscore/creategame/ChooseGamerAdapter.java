package com.android.wolf.werewolfkillerscore.creategame;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.customview.WheelView;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.models.Role;
import com.android.wolf.werewolfkillerscore.sql.RoleDataBaseManager;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/28.
 */

public class ChooseGamerAdapter extends BaseAdapter {

    private ArrayList<Gamer> mGamers;
    private Context mContext;
    private ArrayList<Role> mSelectedRoles = new ArrayList<>();

    private ArrayList<Role> mAllRoles;

    private boolean clickable = true;

    public ChooseGamerAdapter(Context context, ArrayList<Gamer> gamers) {
        mGamers = gamers;
        mContext = context;
        mAllRoles = RoleDataBaseManager.getInstance().queryAll();
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }


    @Override
    public int getCount() {
        return mGamers.size();
    }

    @Override
    public Object getItem(int i) {
        return mGamers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.choose_gamer_item, viewGroup, false);
        }
        TextView nameView = (TextView) view.findViewById(R.id.tv_gamer_name);
        TextView roleView = (TextView) view.findViewById(R.id.tv_role);
        final Gamer gamer = mGamers.get(i);
        final Role role = gamer.getRole();
        if (role != null) {
            roleView.setVisibility(View.VISIBLE);
            roleView.setText(role.getName());
            if (role.getCamp() == 1) {
                roleView.setTextColor(mContext.getResources().getColor(R.color.god_color));
            } else if (role.getCamp() == 2) {
                roleView.setTextColor(mContext.getResources().getColor(R.color.wolf_color));
            } else {
                roleView.setTextColor(mContext.getResources().getColor(R.color.civilian_color));
            }
        } else {
            roleView.setVisibility(View.GONE);
        }
        nameView.setText(gamer.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!clickable) {
                    return;
                }
                if (role != null) {
                    if (mSelectedRoles.contains(role)) {
                        mSelectedRoles.remove(role);
                    }
                    gamer.setRole(null);
                    notifyDataSetChanged();
                } else {
                    chooseRole(gamer);
                }
            }
        });
        return view;
    }

    private void chooseRole(final Gamer gamer) {
        ArrayList<String> datas = new ArrayList<>();
        for (Role role : mAllRoles) {
            datas.add(role.getName());
        }
        View outerView = LayoutInflater.from(mContext).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                gamer.setRole(mAllRoles.get(selectedIndex - 1));
            }
        });
        wv.setItems(datas);
        wv.setSeletion(0);
        new AlertDialog.Builder(mContext)
                .setTitle("设置角色")
                .setView(outerView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mSelectedRoles.contains(gamer.getRole())) {
                            gamer.setRole(null);
                            Toast.makeText(mContext, "重复角色，重新设置", Toast.LENGTH_LONG).show();
                        } else {
                            if (gamer.getRole().getCamp() == 1) {
                                mSelectedRoles.add(gamer.getRole());
                            }
                            notifyDataSetChanged();
                        }
                    }
                })
                .show();
    }

}
