package com.android.wolf.werewolfkillerscore.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.base.BaseActivity;
import com.android.wolf.werewolfkillerscore.billboard.BillboardActivity;
import com.android.wolf.werewolfkillerscore.creategame.CreateGameActivity;
import com.android.wolf.werewolfkillerscore.gamer.GamerListActivity;
import com.android.wolf.werewolfkillerscore.history.HistoryActivity;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.role.RoleListActivity;
import com.android.wolf.werewolfkillerscore.sql.GamerDataBaseManager;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected View createContentView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, root, false);
        view.findViewById(R.id.tv_billboard).setOnClickListener(this);
        view.findViewById(R.id.tv_history).setOnClickListener(this);
        view.findViewById(R.id.tv_gamer_list).setOnClickListener(this);
        view.findViewById(R.id.tv_role_list).setOnClickListener(this);
        view.findViewById(R.id.tv_create_game).setOnClickListener(this);
        return view;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.app_name);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_billboard:
                intent = new Intent(this, BillboardActivity.class);
                break;
            case R.id.tv_history:
                intent = new Intent(this, HistoryActivity.class);
                break;
            case R.id.tv_gamer_list:
                intent = new Intent(this, GamerListActivity.class);
                break;
            case R.id.tv_role_list:
                intent = new Intent(this, RoleListActivity.class);
                break;
            case R.id.tv_create_game:
                int count = GamerDataBaseManager.getInstance().queryCount();
                if (count <= 0) {
                    Toast.makeText(this, "请先添加玩家", Toast.LENGTH_LONG).show();
                    return;
                }
                intent = new Intent(this, CreateGameActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);

    }
}
