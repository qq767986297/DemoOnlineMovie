package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.MineOrderAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MineMovieCommentBean;
import com.bw.movie.bean.MineOrderBean;
import com.bw.movie.bean.SystemMsgBean;
import com.bw.movie.bean.SystemMsgChangeBean;
import com.bw.movie.bean.UserFeedBackBean;
import com.bw.movie.bean.UserFollowMovieBean;
import com.bw.movie.contract.IMineContract;
import com.bw.movie.presenter.MinePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineOrderActivity extends BaseActivity implements IMineContract.IView {


    @BindView(R.id.iv_mine_order_back)
    ImageView iv;
    @BindView(R.id.rv_mine_order)
    RecyclerView rv;

    @Override
    protected BasePresenter initPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_mine_order;
    }

    @Override
    protected void initView() {

    }
    @OnClick(R.id.iv_mine_order_back)
    public void onViewClicked() {
        finish();
    }
    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IMineContract.IPresenter) {
            ((IMineContract.IPresenter) presenter).getUserOrderMovie();
        }
    }

    @Override
    public void onUserOrderMovie(MineOrderBean mineOrderBean) {
        List<MineOrderBean.ResultBean> list = mineOrderBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);
        MineOrderAdapter adapter = new MineOrderAdapter(this, list);
        rv.setAdapter(adapter);
    }

    @Override
    public void onUserFeedBack(UserFeedBackBean userFeedBackBean) {

    }

    @Override
    public void onSystemMsg(SystemMsgBean systemMsgBean) {

    }

    @Override
    public void onSystemMsgChange(SystemMsgChangeBean systemMsgChangeBean) {

    }

    @Override
    public void onUserMovieComment(MineMovieCommentBean mineMovieCommentBean) {

    }

    @Override
    public void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
