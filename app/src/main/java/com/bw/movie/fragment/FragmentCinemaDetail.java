package com.bw.movie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time: 2020/5/8
 * Author: 王冠华
 * Description:
 */
public class FragmentCinemaDetail extends BaseFragment implements ICinemaContract.IView {
    @BindView(R.id.tv_fragment_cinema_detail_location)
    TextView location;
    @BindView(R.id.tv_tv_fragment_cinema_detail_call)
    TextView call;
    @BindView(R.id.tv_tv_fragment_cinema_detail_line)
    TextView line;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new CinemaPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_cinema_detail;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof ICinemaContract.IPresenter){
            int cinemaId = SPUtils.getInt(getActivity(), "cinemaId", "cinemaId");
            (( ICinemaContract.IPresenter)presenter).getCinemaDetail(cinemaId);
        }
    }
    @Override
    public void onCinemaDetail(CinemaDetailBean cinemaDetailBean) {
        CinemaDetailBean.ResultBean bean = cinemaDetailBean.getResult();
        String address = bean.getAddress();
        String phone = bean.getPhone();
        String vehicleRoute = bean.getVehicleRoute();
        location.setText(address);
        call.setText(phone);
        line.setText(vehicleRoute);
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
