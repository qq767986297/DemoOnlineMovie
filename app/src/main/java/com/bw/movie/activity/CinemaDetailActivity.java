package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.bw.movie.contract.IDetailContract;
import com.bw.movie.fragment.FragmentCinemaDetail;
import com.bw.movie.fragment.FragmentCinemaDetailComment;
import com.bw.movie.presenter.CinemaPresenter;
import com.bw.movie.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CinemaDetailActivity extends BaseActivity implements ICinemaContract.IView {


    @BindView(R.id.iv_cinema_detail_back)
    ImageView ivBack;
    @BindView(R.id.tv_cinema_detail_name)
    TextView name;
    @BindView(R.id.iv_cinema_detail_like)
    ImageView like;
    @BindView(R.id.tv_cinema_detail_type)
    TextView type;
    @BindView(R.id.tb_cinema_detail)
    TabLayout tb;
    @BindView(R.id.vp_cinema_detail)
    ViewPager vp;
    @BindView(R.id.tv_cinema_detail_look)
    TextView look;
    private int cinemaId;
    private CinemaDetailBean.ResultBean result;
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    int i=1;
    private int followCinema;
    int heart[] = new int[]{R.mipmap.emptyheart, R.mipmap.emptyheart2};
    @Override
    protected BasePresenter initPresenter() {
        return new CinemaPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_cinema_detail;
    }

    @Override
    protected void initView() {

        FragmentCinemaDetail detail = new FragmentCinemaDetail();
        FragmentCinemaDetailComment comment = new FragmentCinemaDetailComment();
        list.add(detail);
        list.add(comment);
        data.add("影院详情");
        data.add("影院评论");
        tb.addTab(tb.newTab().setText(data.get(0)));
        tb.addTab(tb.newTab().setText(data.get(1)));
        MyViewHolder pager = new MyViewHolder(getSupportFragmentManager());
        vp.setAdapter(pager);
        tb.setupWithViewPager(vp);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        cinemaId = intent.getIntExtra("cinemaId", 0);
        SPUtils.putInt(this,"cinemaId","cinemaId",cinemaId);
        BasePresenter presenter = getPresenter();
        if(presenter instanceof ICinemaContract.IPresenter){
            (( ICinemaContract.IPresenter)presenter).getCinemaDetail(cinemaId);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_cinema_detail_back, R.id.iv_cinema_detail_like, R.id.tv_cinema_detail_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cinema_detail_back:
                finish();
                break;
            case R.id.iv_cinema_detail_like:
                i++;
                if (i % 2 == 1) {
                    BasePresenter presenter = getPresenter();
                    if(presenter instanceof ICinemaContract.IPresenter){
                        (( ICinemaContract.IPresenter)presenter).getCinemaCanelFollow(cinemaId);
                        result.setFollowCinema(2);
                        like.setImageResource(heart[1]);
                    }
                } else {

                    BasePresenter presenter = getPresenter();
                    if(presenter instanceof ICinemaContract.IPresenter){
                        (( ICinemaContract.IPresenter)presenter).getCinemaFollow(cinemaId);
                        result.setFollowCinema(1);
                        like.setImageResource(heart[0]);
                    }
                }
                break;
            case R.id.tv_cinema_detail_look:
                Intent intent = new Intent(CinemaDetailActivity.this, CinemaScheduActivity.class);
                startActivity(intent);
                finish();
                break;
                default:
                    break;
        }
    }
    @Override
    public void onCinemaDetail(CinemaDetailBean cinemaDetailBean) {
        result = cinemaDetailBean.getResult();
        String cinemaName = result.getName();
        String label = result.getLabel();
        name.setText(cinemaName);
        type.setText(label);
        followCinema = result.getFollowCinema();
//        if(followCinema ==1){
//            like.setImageResource(heart[1]);
//        }else {
//            like.setImageResource(heart[0]);
//        }
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
        String message = cinemaFollowBean.getMessage();
        Toast.makeText(this, "关注成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCinemaCanleFollow(CinemaCanelFollowBean cinemaCanelFollowBean) {
        String message = cinemaCanelFollowBean.getMessage();
        Toast.makeText(this, "取消成功", Toast.LENGTH_SHORT).show();
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

    public class MyViewHolder extends FragmentPagerAdapter {
        public MyViewHolder(FragmentManager fm) {
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
}
