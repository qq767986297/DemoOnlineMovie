package com.bw.movie.fragment;

import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MineMovieCommentBean;
import com.bw.movie.bean.MineOrderBean;
import com.bw.movie.bean.SystemMsgBean;
import com.bw.movie.bean.SystemMsgChangeBean;
import com.bw.movie.bean.UserFeedBackBean;
import com.bw.movie.bean.UserFollowMovieBean;
import com.bw.movie.contract.IMineContract;
import com.bw.movie.presenter.MinePresenter;

/**
 * Time: 2020/4/28
 * Author: 王冠华
 * Description:
 */
public class FragmentMineFollowCinema extends BaseFragment implements IMineContract.IView {
    @Override
    protected BasePresenter initPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine_follow_cinema;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean) {

    }

    @Override
    public void onUserOrderMovie(MineOrderBean mineOrderBean) {

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
}
