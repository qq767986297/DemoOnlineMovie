package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Time: 2020/4/20
 * Author: 王冠华
 * Description:
 * a
 */
public class FragmentCinema extends BaseFragment {
    ArrayList<String> data = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    @BindView(R.id.tv_cinema_location)
    TextView tvCinemaLocation;
    @BindView(R.id.iv_cinema_serach)
    ImageView ivCinemaSerach;
    @BindView(R.id.et_cinema)
    EditText etCinema;
    @BindView(R.id.tb_cinema)
    TabLayout tb;
    @BindView(R.id.vp_cinema)
    ViewPager vp;
    Unbinder unbinder;
    @BindView(R.id.iv_cinema_location)
    ImageView ivCinemaLocation;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        data.clear();
        list.clear();
        list.add(new FragmentCinemaRecommend());
        list.add(new FragmentCinemaNearby());
        list.add(new FragmentCinemaLink());
        String city = SPUtils.getString(getActivity(), "city", "city");
        tvCinemaLocation.setText(city);
        data.add("推荐影院");
        data.add("附近影院");
        data.add("海淀区▼");
        tb.addTab(tb.newTab().setText(data.get(0)));
        tb.addTab(tb.newTab().setText(data.get(1)));
        tb.addTab(tb.newTab().setText(data.get(2)));
        MyViewPager pager = new MyViewPager(getChildFragmentManager());
        vp.setAdapter(pager);
        tb.setupWithViewPager(vp);
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

    @OnClick({R.id.iv_cinema_serach, R.id.et_cinema})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cinema_serach:
                etCinema.setVisibility(View.VISIBLE);
                ivCinemaSerach.setVisibility(View.INVISIBLE);
                break;
            case R.id.et_cinema:
//                String str = etCinema.getText().toString();
//                if(str!=null){
//
//                }
                break;
                default:
                    break;
        }
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
