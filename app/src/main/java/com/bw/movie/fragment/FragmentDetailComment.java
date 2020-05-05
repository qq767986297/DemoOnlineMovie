package com.bw.movie.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.DetailCommentAdapter;
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
public class FragmentDetailComment extends BaseFragment implements IDetailContract.IView {
    @BindView(R.id.rv_fragment_detail_comment)
    RecyclerView rv;
    Unbinder unbinder;
    private DetailCommentAdapter adapter;

    @Override
    protected BasePresenter initPresenter() {
        return new DetailPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_detail_comment;
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
            ((IDetailContract.IPresenter) presenter).getMovieComment(id, 1, 5);
        }
    }

    @Override
    public void onMovieComment(MovieCommentBean movieCommentBean) {
        List<MovieCommentBean.ResultBean> list = movieCommentBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        adapter = new DetailCommentAdapter(getActivity(), list);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        adapter.onClick(new DetailCommentAdapter.onGreat() {
            @Override
            public void setOnClick(int i) {
                BasePresenter presenter = getPresenter();
                if (presenter instanceof IDetailContract.IPresenter){
                    ((IDetailContract.IPresenter)presenter).getMovieCommentGreat(i);
                }
            }
        });
    }

    @Override
    public void onMovieCommentGreat(MovieCommentGreatBean movieCommentGreatBean) {
        String message = movieCommentGreatBean.getMessage();
       if(message.equals("点赞成功")){
           Toast.makeText(getActivity(), "点赞成功", Toast.LENGTH_SHORT).show();
           adapter.notifyDataSetChanged();
       }else {
           Toast.makeText(getActivity(), "不能重复点赞", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onMovieFollow(FollowMovieBean followMovieBean) {

    }

    @Override
    public void onCancelMovieFollow(CancelFollowMovieBean cancelFollowMovieBean) {

    }


    @Override
    public void onDetailSuccess(MovieDetailBean movieDetailBean) {

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
