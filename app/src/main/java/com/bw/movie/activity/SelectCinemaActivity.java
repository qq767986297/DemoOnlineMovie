package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.SelectCinemaAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.FindCinemasInfoByRegion;
import com.bw.movie.bean.FindMovieScheduleBean;
import com.bw.movie.bean.FindSeatInfoBean;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.contract.ISelectContract;
import com.bw.movie.presenter.SelectPresenter;
import com.bw.movie.utils.SPUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCinemaActivity extends BaseActivity implements ISelectContract.IView {


    @BindView(R.id.iv_select_cinema)
    SimpleDraweeView ivSelectCinema;
    @BindView(R.id.iv_select_cinema_back)
    ImageView ivSelectCinemaBack;
    @BindView(R.id.tv_select_cinema_name)
    TextView tvSelectCinemaName;
    @BindView(R.id.tv_select_cinema_time)
    TextView tvSelectCinemaTime;
    @BindView(R.id.tv_select_cinema_score)
    TextView tvSelectCinemaScore;
    @BindView(R.id.tv_select_cinema_dictor)
    TextView tvSelectCinemaDictor;
    @BindView(R.id.tv_select_cinema_region)
    TextView tvSelectCinemaRegion;
    @BindView(R.id.tv_select_cinema_day)
    TextView tvSelectCinemaDay;
    @BindView(R.id.tv_select_cinema_price)
    TextView tvSelectCinemaPrice;
    @BindView(R.id.iv_select_cinema_search)
    ImageView ivSelectCinemaSearch;
    @BindView(R.id.rv_select_cinema)
    RecyclerView rvSelectCinema;


    @Override
    protected BasePresenter initPresenter() {
        return new SelectPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_select_cinema;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initData() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void init(MovieDetailBean movieDetailBean){
        MovieDetailBean.ResultBean result = movieDetailBean.getResult();
        String imageUrl = result.getImageUrl();
        SPUtils.putString(this,"img","img",imageUrl);
        int movieId = result.getMovieId();
        SPUtils.putInt(this,"movieId","movieId",movieId);
        BasePresenter presenter = getPresenter();
        if(presenter instanceof ISelectContract.IPresenter){
            ((ISelectContract.IPresenter)presenter).getCinemaByRegion(movieId,1,1,5);
        }
        Log.i("MOVIEID",movieId+"");
        String name = result.getName();
        List<MovieDetailBean.ResultBean.MovieDirectorBean> movieDirector = result.getMovieDirector();
        for (int i=0;i<movieDirector.size();i++){
            MovieDetailBean.ResultBean.MovieDirectorBean bean = movieDirector.get(i);
            String beanName = bean.getName();
            tvSelectCinemaDictor.setText(beanName);
        }
        double score = result.getScore();
        String duration = result.getDuration();
        tvSelectCinemaName.setText(name);
        tvSelectCinemaTime.setText(duration+"分钟");
        tvSelectCinemaScore.setText(score+"分");
        ivSelectCinema.setImageURI(imageUrl);
    }
    @Override
    public void onCinemaByRegion(FindCinemasInfoByRegion findCinemasInfoByRegion) {
        List<FindCinemasInfoByRegion.ResultBean> list = findCinemasInfoByRegion.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        SelectCinemaAdapter adapter = new SelectCinemaAdapter(this, list);
        rvSelectCinema.setLayoutManager(manager);
        rvSelectCinema.setAdapter(adapter);
        adapter.Click(new SelectCinemaAdapter.onClick() {
            @Override
            public void setClick(int cinemaId) {
                Intent intent = new Intent(SelectCinemaActivity.this, FindSeatInfoActivity.class);
                intent.putExtra("cinemaId",cinemaId);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onShowSeat(FindSeatInfoBean findSeatInfoBean) {

    }

    @Override
    public void onFindMovieSchedule(FindMovieScheduleBean findMovieScheduleBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_select_cinema_back, R.id.tv_select_cinema_region, R.id.tv_select_cinema_day, R.id.tv_select_cinema_price, R.id.iv_select_cinema_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_select_cinema_back:
                finish();
                break;
            case R.id.tv_select_cinema_region:
                break;
            case R.id.tv_select_cinema_day:
                break;
            case R.id.tv_select_cinema_price:
                break;
            case R.id.iv_select_cinema_search:
                break;
                default:
                    break;
        }
    }
}
