package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaDetailActivity;
import com.bw.movie.adapter.CinemaNearbyAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaCanelFollowBean;
import com.bw.movie.bean.CinemaCommentGoodBean;
import com.bw.movie.bean.CinemaDetailBean;
import com.bw.movie.bean.CinemaDetailCommentBean;
import com.bw.movie.bean.CinemaFollowBean;
import com.bw.movie.bean.CinemaLinkBean;
import com.bw.movie.bean.CinemaNearbyBean;
import com.bw.movie.bean.CinemaRecommendBean;
import com.bw.movie.bean.CinemaRegionBean;
import com.bw.movie.bean.CinemaScheduleListBean;
import com.bw.movie.bean.FindDataBean;
import com.bw.movie.contract.ICinemaContract;
import com.bw.movie.presenter.CinemaPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time: 2020/5/6
 * Author: 王冠华
 * Description:
 */
public class FragmentCinemaNearby extends BaseFragment implements ICinemaContract.IView {
    @BindView(R.id.rv_cinema_nearby)
    RecyclerView rv;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new CinemaPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_cinema_nearby;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof ICinemaContract.IPresenter) {
            ((ICinemaContract.IPresenter) presenter).getCinemaNearby("166", "40", 1, 10);
        }
    }

    @Override
    public void onCinemaRecommend(CinemaRecommendBean cinemaRecommendBean) {

    }

    @Override
    public void onCinemaNearby(CinemaNearbyBean cinemaNearbyBean) {
        List<CinemaNearbyBean.ResultBean> list = cinemaNearbyBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        CinemaNearbyAdapter adapter = new CinemaNearbyAdapter(getActivity(), list);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        adapter.Click(new CinemaNearbyAdapter.onClick() {
            @Override
            public void setClick(int id) {
                Intent intent = new Intent(getActivity(), CinemaDetailActivity.class);
                intent.putExtra("cinemaId",id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCinemaRegion(CinemaRegionBean cinemaRegionBean) {

    }

    @Override
    public void onCinemaLink(CinemaLinkBean cinemaLinkBean) {

    }

    @Override
    public void onCinemaDetail(CinemaDetailBean cinemaDetailBean) {

    }

    @Override
    public void onCinemaDetailComment(CinemaDetailCommentBean cinemaDetailCommentBean) {

    }

    @Override
    public void onCinemaFollow(CinemaFollowBean cinemaFollowBean) {

    }

    @Override
    public void onCinemaCanleFollow(CinemaCanelFollowBean cinemaCanelFollowBean) {

    }

    @Override
    public void onCinemaCommentGood(CinemaCommentGoodBean cinemaCommentGoodBean) {

    }

    @Override
    public void onCinemaSchedu(CinemaScheduleListBean cinemaScheduleListBean) {

    }

    @Override
    public void onCinemaData(FindDataBean findDataBean) {

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
