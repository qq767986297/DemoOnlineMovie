package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import com.bw.movie.presenter.HomePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindMovieActivity extends BaseActivity implements HomeContract.IHomeView {


    @BindView(R.id.home_search_back)
    ImageView iv;
    @BindView(R.id.et_home_search)
    EditText et;
    @BindView(R.id.rv_home_search)
    RecyclerView rv;
    @BindView(R.id.ll_home_search)
    LinearLayout ll;
    private List<HomeSearchMovieBean.ResultBean> list;
    private HomeSearchAdapter adapter;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_find_movie;
    }

    @Override
    protected void initView() {

    }
    @Override
    public void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
    @Override
    protected void initData() {
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

                }
            }
        });
    }
    @OnClick(R.id.home_search_back)
    public void onViewClicked() {
        //点击退出
        finish();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void init(Integer id){
        Intent intent = new Intent(FindMovieActivity.this, MoviesDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    @Override
    public void onHomeSerachMovieSuccess(HomeSearchMovieBean homeSearchMovieBean) {
        list = homeSearchMovieBean.getResult();
        String message = homeSearchMovieBean.getMessage();
        ll.setVisibility(View.GONE);
        if (message.equals("查询成功")) {
            //查询成功隐藏图片
            ll.setVisibility(View.GONE);
            LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            adapter = new HomeSearchAdapter(this, list);
            rv.setLayoutManager(manager);
            rv.setAdapter(adapter);
            adapter.setClick(new HomeHotAdapter.onClick() {
                @Override
                public void setOnClick(int position) {
                    HomeSearchMovieBean.ResultBean bean = list.get(position);
                    int movieId = bean.getMovieId();
                    init(movieId);
                }
            });
        } else if (et == null) {
            //查询失败显示图片
//            list.clear();
            rv.setVisibility(View.GONE);
            ll.setVisibility(View.VISIBLE);
        }
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
