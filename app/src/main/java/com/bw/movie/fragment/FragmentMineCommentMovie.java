package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.MineMovieCommentAdapter;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time: 2020/5/6
 * Author: 王冠华
 * Description:
 */
public class FragmentMineCommentMovie extends BaseFragment implements IMineContract.IView {
    @BindView(R.id.rv_fragment_mine_comment_movie)
    RecyclerView rv;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine_comment_movie;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof IMineContract.IPresenter){
            ((IMineContract.IPresenter)presenter).getUserMovieComment(1,5);
        }
    }

    @Override
    public void onUserMovieComment(MineMovieCommentBean mineMovieCommentBean) {
        Log.i("xxx","V层");
        List<MineMovieCommentBean.ResultBean> list = mineMovieCommentBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        MineMovieCommentAdapter adapter = new MineMovieCommentAdapter(getActivity(), list);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
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
}
