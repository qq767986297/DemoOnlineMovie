package com.bw.movie.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
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
import com.bw.movie.fragment.FragmentSchedu;
import com.bw.movie.presenter.CinemaPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CinemaScheduActivity extends BaseActivity implements ICinemaContract.IView {


    @BindView(R.id.iv_cinema_schedu)
    ImageView iv;
    @BindView(R.id.tb_cinema_schedu)
    TabLayout tb;
    @BindView(R.id.vp_cinema_schedu)
    ViewPager vp;

    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    @Override
    protected BasePresenter initPresenter() {
        return new CinemaPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_cinema_schedu;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof ICinemaContract.IPresenter){
            (( ICinemaContract.IPresenter)presenter).getCinemaData();
        }


    }

    @Override
    public void onCinemaData(FindDataBean findDataBean) {
        List<String>  result = findDataBean.getResult();
        data.addAll(result);
        for (int i=0;i<result.size();i++){
            list.add(new FragmentSchedu());
            String s = result.get(i);
            data.add(s);
        }
        tb.addTab(tb.newTab().setText(data.get(0)));
        tb.addTab(tb.newTab().setText(data.get(1)));
        tb.addTab(tb.newTab().setText(data.get(2)));
        tb.addTab(tb.newTab().setText(data.get(3)));
        tb.addTab(tb.newTab().setText(data.get(4)));
        tb.addTab(tb.newTab().setText(data.get(5)));
        tb.addTab(tb.newTab().setText(data.get(6)));
        tb.addTab(tb.newTab().setText(data.get(7)));
        MyViewPager pager = new MyViewPager(getSupportFragmentManager());
        vp.setAdapter(pager);
        tb.setupWithViewPager(vp);

    }
    public class MyViewPager extends FragmentPagerAdapter {
        public MyViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return data.get(position);
        }
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_cinema_schedu)
    public void onViewClicked() {
        finish();
    }
}
