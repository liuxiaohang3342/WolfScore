package com.android.wolf.werewolfkillerscore.role;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.wolf.werewolfkillerscore.R;
import com.android.wolf.werewolfkillerscore.base.BaseActivity;
import com.android.wolf.werewolfkillerscore.interfaces.Constant;
import com.android.wolf.werewolfkillerscore.models.Role;
import com.android.wolf.werewolfkillerscore.sql.RoleDataBaseManager;

/**
 * Created by lxh on 2017/5/28.
 */

public class CreateRoleActivity extends BaseActivity implements View.OnClickListener {

    private EditText mRoleName;
    private EditText mRoleDesc;
    private EditText mRoleScore;
    private RadioGroup mCamp;

    @Override
    protected View createContentView(ViewGroup root) {
        View view = LayoutInflater.from(this).inflate(R.layout.add_new_role, root, false);
        mRoleName = (EditText) view.findViewById(R.id.et_gamer_names);
        mRoleDesc = (EditText) view.findViewById(R.id.et_gamer_desc);
        mRoleScore = (EditText) view.findViewById(R.id.et_role_score);
        mCamp = (RadioGroup) view.findViewById(R.id.rg_camp);
        view.findViewById(R.id.tv_add_gamer).setOnClickListener(this);
        return view;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.add_new_role);
    }

    @Override
    public void onClick(View view) {
        String name = mRoleName.getText().toString();
        String rule = mRoleDesc.getText().toString();
        String score = mRoleScore.getText().toString();
        int buttonid = mCamp.getCheckedRadioButtonId();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(rule) || TextUtils.isEmpty(score) || buttonid == 0) {
            Toast.makeText(this, "请把信息填写完整", Toast.LENGTH_LONG).show();
            return;
        }

        Role role = new Role();
        role.setName(name);
        role.setRules(rule);
        role.setScore(Integer.valueOf(score));
        if (buttonid == R.id.rb_god_camp) {
            role.setCamp(Constant.GOD_CAMP);
        } else if (buttonid == R.id.rb_wolf_camp) {
            role.setCamp(Constant.WOLF_CAMP);
        } else {
            role.setCamp(Constant.GOOD_CAMP);
        }
        RoleDataBaseManager.getInstance().insert(role);
        Intent intent = new Intent();
        intent.putExtra("role", role);
        setResult(0, intent);
        finish();
    }


}
