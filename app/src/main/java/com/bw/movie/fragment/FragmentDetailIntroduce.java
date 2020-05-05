package com.bw.movie.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.DetailIntroduceAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.bean.MovieCommentBean;
import com.bw.movie.bean.MovieCommentGreatBean;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.contract.IDetailContract;
import com.bw.movie.presenter.DetailPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time: 2020/4/24
 * Author: 王冠华
 * Description:
 */
public class FragmentDetailIntroduce extends BaseFragment implements IDetailContract.IView {
    @BindView(R.id.rv_fragment_detail_introduce)
    RecyclerView rv;
    Unbinder unbinder;
    @BindView(R.id.tv_context)
    TextView tvContext;
    @BindView(R.id.iv_item_detail_director)
    SimpleDraweeView ivItemDetailDirector;
    @BindView(R.id.tv_item_detail_director_name)
    TextView tvItemDetailDirectorName;
    private int movieId;

    @Override
    protected BasePresenter initPresenter() {
        return new DetailPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_detail_introduce;
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
        MovieDetailBean.ResultBean list = movieDetailBean.getResult();
        List<MovieDetailBean.ResultBean.MovieActorBean> actor = list.getMovieActor();
        String summary = list.getSummary();
        tvContext.setText(summary);
        List<MovieDetailBean.ResultBean.MovieDirectorBean> list1 = list.getMovieDirector();
        MovieDetailBean.ResultBean.MovieDirectorBean bean = list1.get(0);
        String name = bean.getName();
        String photo = bean.getPhoto();
        tvItemDetailDirectorName.setText(name);
        Uri uri = Uri.parse(photo);
        ivItemDetailDirector.setImageURI(uri);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        DetailIntroduceAdapter adapter = new DetailIntroduceAdapter(getActivity(), actor);
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
