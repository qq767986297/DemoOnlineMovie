package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.activity.MineFollowActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Time: 2020/4/20
 * Author: 王冠华
 * Description:
 */
public class FragmentMine extends BaseFragment {
    @BindView(R.id.iv_mine_userinfo)
    ImageView ivMineUserinfo;
    @BindView(R.id.ticket)
    RelativeLayout ticket;
    @BindView(R.id.iv_mine_follow)
    ImageView ivMineFollow;
    @BindView(R.id.iv_mine_order)
    ImageView ivMineOrder;
    @BindView(R.id.iv_mine_record)
    ImageView ivMineRecord;
    @BindView(R.id.iv_mine_history)
    ImageView ivMineHistory;
    @BindView(R.id.iv_mine_comment)
    ImageView ivMineComment;
    @BindView(R.id.iv_mine_idea)
    ImageView ivMineIdea;
    @BindView(R.id.iv_mine_setting)
    ImageView ivMineSetting;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_mine_userinfo, R.id.ticket, R.id.iv_mine_follow, R.id.iv_mine_order, R.id.iv_mine_record,
            R.id.iv_mine_history, R.id.iv_mine_comment, R.id.iv_mine_idea, R.id.iv_mine_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_userinfo:
                break;
            case R.id.ticket:
                break;
            case R.id.iv_mine_follow:
                Intent intent = new Intent(getActivity(), MineFollowActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_mine_order:
                break;
            case R.id.iv_mine_record:
                break;
            case R.id.iv_mine_history:
                break;
            case R.id.iv_mine_comment:
                break;
            case R.id.iv_mine_idea:
                break;
            case R.id.iv_mine_setting:
                break;
                default:
                    break;
        }
    }
}
