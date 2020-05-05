package com.bw.movie.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.DetailNoticeAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.bean.MovieCommentBean;
import com.bw.movie.bean.MovieCommentGreatBean;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.contract.IDetailContract;
import com.bw.movie.presenter.DetailPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time: 2020/4/24
 * Author: 王冠华
 * Description:
 */
public class FragmentDetailNotice extends BaseFragment implements IDetailContract.IView {
    @BindView(R.id.rv_fragment_detail_notice)
    RecyclerView rv;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new DetailPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_detail_notice;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        SharedPreferences sp = getActivity().getSharedPreferences("movie", Context.MODE_PRIVATE);
        int id = sp.getInt("id", 0);
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IDetailContract.IPresenter) {
            ((IDetailContract.IPresenter) presenter).getDetail(id);
        }
    }

    @Override
    public void onDetailSuccess(MovieDetailBean movieDetailBean) {
        MovieDetailBean.ResultBean result = movieDetailBean.getResult();
        List<MovieDetailBean.ResultBean.ShortFilmListBean> list = result.getShortFilmList();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        DetailNoticeAdapter adapter = new DetailNoticeAdapter(getActivity(), list);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

    @Override
    public void onMovieComment(MovieCommentBean movieCommentBean) {

    }

    @Override
    public void onMovieFollow(FollowMovieBean followMovieBean) {

    }

    @Override
    public void onCancelMovieFollow(CancelFollowMovieBean cancelFollowMovieBean) {

    }

    @Override
    public void onMovieCommentGreat(MovieCommentGreatBean movieCommentGreatBean) {

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
