package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.MoviesDetailActivity;
import com.bw.movie.adapter.HomeHotAdapter;
import com.bw.movie.adapter.HomeMoreSoonAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.HomeBannerBean;
import com.bw.movie.bean.HomeHotMovieBean;
import com.bw.movie.bean.HomeReleaseMovieBean;
import com.bw.movie.bean.HomeSearchMovieBean;
import com.bw.movie.bean.HomeSoonMovieBean;
import com.bw.movie.bean.MovieReserveBean;
import com.bw.movie.contract.HomeContract;
import com.bw.movie.presenter.HomePresenter;
import com.bw.movie.utils.RetrofiManger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time: 2020/4/23
 * Author: 王冠华
 * Description:
 */
public class FragmentMoreSoon extends BaseFragment implements HomeContract.IHomeView {
    @BindView(R.id.rv_home_more_soon)
    RecyclerView rv;
    Unbinder unbinder;
    private int movieId;
    private HomeMoreSoonAdapter adapter;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_more_soon;
    }

    @Override
    protected void initView(View view) {

    }
    @Override
    public void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void init(Integer id){
        Intent intent = new Intent(getActivity(), MoviesDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    @Override
    protected void initData() {
        boolean netWork = RetrofiManger.getInstance().isNetWork(getActivity());
        if (netWork) {
            BasePresenter presenter = getPresenter();
            if (presenter instanceof HomeContract.IHomePresenter) {
                ((HomeContract.IHomePresenter) presenter).getHomeSoonMovie(1, 15);
            }
        } else {
            Toast.makeText(getActivity(), "无网络", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onHomeSoonMovieSuccess(HomeSoonMovieBean homeSoonMovieBean) {
        final List<HomeSoonMovieBean.ResultBean> list = homeSoonMovieBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);
        adapter = new HomeMoreSoonAdapter(getActivity(), list);
        rv.setAdapter(adapter);
        adapter.setClick(new HomeHotAdapter.onClick() {
            @Override
            public void setOnClick(int position) {
                HomeSoonMovieBean.ResultBean bean = list.get(position);
                int movieId = bean.getMovieId();
                init(movieId);
            }
        });
        adapter.setbtClick(new HomeMoreSoonAdapter.onbtClick() {
            @Override
            public void setOnbtClick(int movieId) {
                BasePresenter presenter = getPresenter();
                if (presenter instanceof HomeContract.IHomePresenter) {
                    ((HomeContract.IHomePresenter) presenter).getMovieReserve(movieId);
                }
            }
        });
    }

    @Override
    public void onBannerSuccess(HomeBannerBean homeBannerBean) {

    }

    @Override
    public void onHomeReleaseMovieSuccess(HomeReleaseMovieBean homeReleaseMovieBean) {

    }



    @Override
    public void onHomeHotMovieSuccess(HomeHotMovieBean homeHotMovieBean) {

    }

    @Override
    public void onHomeSerachMovieSuccess(HomeSearchMovieBean homeSearchMovieBean) {

    }

    @Override
    public void onMovieReserve(MovieReserveBean movieReserveBean) {
        String message = movieReserveBean.getMessage();
        adapter.notifyDataSetChanged();
        if(message.equals("预约成功")){
            Toast.makeText(getActivity(), "预约成功", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getActivity(), "预约失败", Toast.LENGTH_SHORT).show();
        }
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
