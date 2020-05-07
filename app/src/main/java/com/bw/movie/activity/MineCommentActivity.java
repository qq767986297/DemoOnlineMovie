package com.bw.movie.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.FragmentMineCommentCinema;
import com.bw.movie.fragment.FragmentMineCommentMovie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineCommentActivity extends BaseActivity {

    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    @BindView(R.id.iv_mine_comment_back)
    ImageView iv;
    @BindView(R.id.tb_mine_comment)
    TabLayout tb;
    @BindView(R.id.vp_mine_comment)
    ViewPager vp;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_mine_comment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        FragmentMineCommentMovie movie = new FragmentMineCommentMovie();
        FragmentMineCommentCinema cinema = new FragmentMineCommentCinema();
        list.add(movie);
        list.add(cinema);
        data.add("电影");
        data.add("影院");
        tb.addTab(tb.newTab().setText(data.get(0)));
        tb.addTab(tb.newTab().setText(data.get(1)));
        MyViewPager pager = new MyViewPager(getSupportFragmentManager());
        vp.setAdapter(pager);
        tb.setupWithViewPager(vp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_mine_comment_back)
    public void onViewClicked() {
        finish();
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
}
