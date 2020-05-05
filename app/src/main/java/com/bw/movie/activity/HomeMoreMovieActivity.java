package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.HomeHotAdapter;
import com.bw.movie.adapter.HomeSearchAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.HomeBannerBean;
import com.bw.movie.bean.HomeHotMovieBean;
import com.bw.movie.bean.HomeReleaseMovieBean;
import com.bw.movie.bean.HomeSearchMovieBean;
import com.bw.movie.bean.HomeSoonMovieBean;
import com.bw.movie.bean.MovieReserveBean;
import com.bw.movie.contract.HomeContract;
import com.bw.movie.fragment.FragmentMoreHot;
import com.bw.movie.fragment.FragmentMoreRelease;
import com.bw.movie.fragment.FragmentMoreSoon;
import com.bw.movie.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeMoreMovieActivity extends BaseActivity implements HomeContract.IHomeView {


    @BindView(R.id.iv_home_more_back)
    ImageView ivHomeMoreBack;
    @BindView(R.id.iv_home_more_serach)
    ImageView ivHomeMoreSerach;
    @BindView(R.id.tb_more)
    TabLayout tb;
    @BindView(R.id.vp_more)
    ViewPager vp;
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    @BindView(R.id.et_more_search)
    EditText et;
    private List<HomeSearchMovieBean.ResultBean> result;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home_more_movie;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        FragmentMoreRelease release = new FragmentMoreRelease();
        FragmentMoreSoon soon = new FragmentMoreSoon();
        FragmentMoreHot hot = new FragmentMoreHot();
        list.add(release);
        list.add(soon);
        list.add(hot);
        data.add("正在热映");
        data.add("即将上映");
        data.add("热门电影");
        MyViewPage page = new MyViewPage(getSupportFragmentManager());
        vp.setAdapter(page);
        Intent intent = getIntent();
        int num = intent.getIntExtra("num", 0);
        vp.setCurrentItem(num);
        tb.addTab(tb.newTab().setText(data.get(0)));
        tb.addTab(tb.newTab().setText(data.get(1)));
        tb.addTab(tb.newTab().setText(data.get(2)));
        tb.setupWithViewPager(vp);
        et.setVisibility(View.GONE);
        //文本改变监听
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                BasePresenter presenter = getPresenter();
                if (presenter instanceof HomeContract.IHomePresenter) {
                    String str = et.getText().toString();
                    ((HomeContract.IHomePresenter) presenter).getHomeSerachMovie(str, 1, 5);
//                    Intent intent1 = new Intent(HomeMoreMovieActivity.this, FindMovieActivity.class);
//                    intent1.putExtra("et",str);
//                    startActivity(intent1);
                }
            }
        });
    }

    @OnClick({R.id.iv_home_more_back, R.id.iv_home_more_serach})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.iv_home_more_back:
                finish();
                break;
            case R.id.iv_home_more_serach:
//                //点击显示输入框
////                et.setVisibility(View.VISIBLE);
////                ivHomeMoreSerach.setVisibility(View.GONE);

                break;
                default:
                    break;
        }
    }
    @Override
    public void onHomeSerachMovieSuccess(HomeSearchMovieBean homeSearchMovieBean) {
        result = homeSearchMovieBean.getResult();
        String message = homeSearchMovieBean.getMessage();
//        if (message.equals("查询成功")) {
//            //查询成功隐藏图片
//
//            LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//            HomeSearchAdapter adapter = new HomeSearchAdapter(this, result);
//            rv.setLayoutManager(manager);
//            rv.setAdapter(adapter);
//            adapter.setClick(new HomeHotAdapter.onClick() {
//                @Override
//                public void setOnClick(int position) {
//                    HomeSearchMovieBean.ResultBean bean = result.get(position);
//                    int movieId = bean.getMovieId();
//                    init(movieId);
//                }
//            });
//        } else if (et == null) {
//            //查询失败显示图片
//            list.clear();
//        }
    }

    @Override
    public void onMovieReserve(MovieReserveBean movieReserveBean) {

    }

    @Override
    public void onBannerSuccess(HomeBannerBean homeBannerBean) {

    }

    @Override
    public void onHomeReleaseMovieSuccess(HomeReleaseMovieBean homeReleaseMovieBean) {

    }

    @Override
    public void onHomeSoonMovieSuccess(HomeSoonMovieBean homeSoonMovieBean) {

    }

    @Override
    public void onHomeHotMovieSuccess(HomeHotMovieBean homeHotMovieBean) {

    }



    private class MyViewPage extends FragmentPagerAdapter {
        public MyViewPage(FragmentManager fm) {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
