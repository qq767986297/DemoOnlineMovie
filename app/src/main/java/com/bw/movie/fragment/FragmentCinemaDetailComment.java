package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaDetailCommentAdapter;
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
import com.bw.movie.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time: 2020/5/8
 * Author: 王冠华
 * Description:
 */
public class FragmentCinemaDetailComment extends BaseFragment implements ICinemaContract.IView {
    @BindView(R.id.rv_fragment_detail_comment)
    RecyclerView rv;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new CinemaPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_cinema_comment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof ICinemaContract.IPresenter) {
            int cinemaId = SPUtils.getInt(getActivity(), "cinemaId", "cinemaId");
            ((ICinemaContract.IPresenter) presenter).getCinemaDetailComment(cinemaId, 1, 10);
        }
    }

    @Override
    public void onCinemaDetailComment(CinemaDetailCommentBean cinemaDetailCommentBean) {
        List<CinemaDetailCommentBean.ResultBean> list = cinemaDetailCommentBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        final CinemaDetailCommentAdapter adapter = new CinemaDetailCommentAdapter(getActivity(), list);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        adapter.Click(new CinemaDetailCommentAdapter.onClick() {
            @Override
            public void setOnClick(int commentId) {
                BasePresenter presenter = getPresenter();
                if (presenter instanceof ICinemaContract.IPresenter) {
                    ((ICinemaContract.IPresenter) presenter).getCinemaCommentGood(commentId);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    @Override
    public void onCinemaCommentGood(CinemaCommentGoodBean cinemaCommentGoodBean) {
        String message = cinemaCommentGoodBean.getMessage();
        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCinemaSchedu(CinemaScheduleListBean cinemaScheduleListBean) {

    }

    @Override
    public void onCinemaData(FindDataBean findDataBean) {

    }

    @Override
    public void onCinemaRecommend(CinemaRecommendBean cinemaRecommendBean) {

    }

    @Override
    public void onCinemaNearby(CinemaNearbyBean cinemaNearbyBean) {

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
    public void onCinemaFollow(CinemaFollowBean cinemaFollowBean) {

    }

    @Override
    public void onCinemaCanleFollow(CinemaCanelFollowBean cinemaCanelFollowBean) {

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
