package com.bw.movie.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBackSuccessActivity extends BaseActivity {


    @BindView(R.id.iv_mine_feedback_back)
    ImageView iv;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_feed_back_success;
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.iv_mine_feedback_back)
    public void onViewClicked() {
        finish();
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

}
