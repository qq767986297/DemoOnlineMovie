package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineSettingActivity extends BaseActivity {


    @BindView(R.id.iv_mine_setting_back)
    ImageView ivMineSettingBack;
    @BindView(R.id.tv_mine_setting_cache)
    TextView tvMineSettingCache;
    @BindView(R.id.iv_mine_setting_update)
    ImageView ivMineSettingUpdate;
    @BindView(R.id.iv_mine_setting_reset)
    ImageView ivMineSettingReset;
    @BindView(R.id.bt_mine_setting_quit)
    Button btMineSettingQuit;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_mine_setting;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_mine_setting_back, R.id.tv_mine_setting_cache, R.id.iv_mine_setting_update, R.id.iv_mine_setting_reset, R.id.bt_mine_setting_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_setting_back:
                finish();
                break;
            case R.id.tv_mine_setting_cache:
                break;
            case R.id.iv_mine_setting_update:
                Intent intent1 = new Intent(MineSettingActivity.this, NewVersionActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_mine_setting_reset:
                Intent intent = new Intent(MineSettingActivity.this, MineResetPassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_mine_setting_quit:
                break;
                default:
                    break;
        }
    }
}
