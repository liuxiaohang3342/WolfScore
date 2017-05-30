package com.android.wolf.werewolfkillerscore.gamer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.base.BaseActivity;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.sql.GamerDataBaseManager;

/**
 * Created by lxh on 2017/5/28.
 */

public class CreateGamerActivity extends BaseActivity implements View.OnClickListener {

    private EditText mGamerName;
    private EditText mGamerDesc;

    @Override
    protected View createContentView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.add_new_gamer, root, false);
        mGamerName = (EditText) view.findViewById(R.id.et_gamer_names);
        mGamerDesc = (EditText) view.findViewById(R.id.et_gamer_desc);
        view.findViewById(R.id.tv_add_gamer).setOnClickListener(this);
        return view;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.add_new_gamer);
    }

    @Override
    public void onClick(View view) {
        Gamer gamer = new Gamer();
        gamer.setName(mGamerName.getText().toString());
        gamer.setDesc(mGamerDesc.getText().toString());
        GamerDataBaseManager.getInstance().insert(gamer);
        Intent intent = new Intent();
        intent.putExtra("gamer", gamer);
        setResult(0, intent);
        finish();
    }


}
