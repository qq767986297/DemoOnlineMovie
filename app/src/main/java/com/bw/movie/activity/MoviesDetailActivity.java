package com.bw.movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.bean.MovieCommentBean;
import com.bw.movie.bean.MovieCommentGreatBean;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.contract.IDetailContract;
import com.bw.movie.custom.DrawerLayout;
import com.bw.movie.fragment.FragmentDetailComment;
import com.bw.movie.fragment.FragmentDetailIntroduce;
import com.bw.movie.fragment.FragmentDetailNotice;
import com.bw.movie.fragment.FragmentDetailPhoto;
import com.bw.movie.presenter.DetailPresenter;
import com.bw.movie.utils.RetrofiManger;
import com.bw.movie.utils.SPUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MoviesDetailActivity extends BaseActivity implements IDetailContract.IView {


    @BindView(R.id.iv_detail)
    SimpleDraweeView ivDetail;
    @BindView(R.id.iv_detail_back)
    ImageView ivDetailBack;
    @BindView(R.id.tv_ping)
    TextView tvPing;
    @BindView(R.id.tv_detail_scroe)
    TextView tvDetailScroe;
    @BindView(R.id.tv_detail_comment)
    TextView tvDetailComment;
    @BindView(R.id.tv_detail_name)
    TextView tvDetailName;
    @BindView(R.id.tv_detail_type)
    TextView tvDetailType;
    @BindView(R.id.tv_detail_duration)
    TextView tvDetailDuration;
    @BindView(R.id.tv_detail_time)
    TextView tvDetailTime;

    @BindView(R.id.iv_focus)
    ImageView ivFocus;
    //    @BindView(R.id.iv_detail_pull)
//    ImageView ivDetailPull;
    @BindView(R.id.bt_detail_write)
    Button btDetailWrite;
    @BindView(R.id.bt_detail_buy)
    Button btDetailBuy;
    @BindView(R.id.tb_detail)
    TabLayout tb;
    @BindView(R.id.vp_detail)
    ViewPager vp;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.rl_detail_parent)
    RelativeLayout rlDetailParent;
    private int id;
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    int i = 1;
    int heart[] = new int[]{R.mipmap.emptyheart, R.mipmap.emptyheart2};
    private String listName;

    @Override
    protected BasePresenter initPresenter() {
        return new DetailPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_movies_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        SharedPreferences sp = getSharedPreferences("movie", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("id", id);
        edit.commit();
        FragmentDetailIntroduce introduce = new FragmentDetailIntroduce();
        FragmentDetailNotice notice = new FragmentDetailNotice();
        FragmentDetailPhoto photo = new FragmentDetailPhoto();
        FragmentDetailComment comment = new FragmentDetailComment();
        list.add(introduce);
        list.add(notice);
        list.add(photo);
        list.add(comment);
        data.add("介绍");
        data.add("预告");
        data.add("剧照");
        data.add("影评");
        MyViewPage page = new MyViewPage(getSupportFragmentManager());
        vp.setAdapter(page);
        tb.addTab(tb.newTab().setText(data.get(0)));
        tb.addTab(tb.newTab().setText(data.get(1)));
        tb.addTab(tb.newTab().setText(data.get(2)));
        tb.addTab(tb.newTab().setText(data.get(3)));
        tb.setupWithViewPager(vp);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @OnClick({R.id.iv_detail_back, R.id.bt_detail_write, R.id.bt_detail_buy, R.id.iv_focus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_detail_back:
                finish();
                break;
            case R.id.bt_detail_write:
                Intent intent = new Intent(MoviesDetailActivity.this, AddMovieCommentActivity.class);
                intent.putExtra("movieId",id);
                intent.putExtra("name",listName);
                startActivity(intent);
                break;
            case R.id.bt_detail_buy:
                break;
            case R.id.iv_focus:
                //点击关注
                i++;
                if (i % 2 == 1) {
                    BasePresenter presenter = getPresenter();
                    if (presenter instanceof IDetailContract.IPresenter) {
                        ((IDetailContract.IPresenter) presenter).getCancelMovieFollow(id);
                    }
                    ivFocus.setImageResource(heart[1]);
                    tvFollow.setText("关注");
                } else {
                    BasePresenter presenter = getPresenter();
                    if (presenter instanceof IDetailContract.IPresenter) {
                        ((IDetailContract.IPresenter) presenter).getMovieFollow(id);
                        SPUtils.putInt(this, "followMovieId", "followMovieId", id);
                    }
                    ivFocus.setImageResource(heart[0]);
                    tvFollow.setText("已关注");
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {
        DrawerLayout drawerLayout = new DrawerLayout(MoviesDetailActivity.this);
        drawerLayout.setInitialState(DrawerLayout.State.Close);
        boolean netWork = RetrofiManger.getInstance().isNetWork(this);
        if (netWork) {
            BasePresenter presenter = getPresenter();
            if (presenter instanceof IDetailContract.IPresenter) {
                ((IDetailContract.IPresenter) presenter).getDetail(id);
            }
        } else {

        }

    }

    @Override
    public void onDetailSuccess(MovieDetailBean movieDetailBean) {
        MovieDetailBean.ResultBean list = movieDetailBean.getResult();
        String imageUrl = list.getImageUrl();
        listName = list.getName();
        Uri uri = Uri.parse(imageUrl);
        ivDetail.setImageURI(uri);
        tvDetailName.setText(list.getName());
        tvDetailComment.setText(list.getCommentNum() + "条");
        tvDetailDuration.setText(list.getDuration());
        tvDetailScroe.setText(list.getScore() + "分");
        tvDetailType.setText(list.getMovieType());
        Date date = new Date(list.getReleaseTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        tvDetailTime.setText(format + " " + list.getPlaceOrigin());

    }

    @Override
    public void onMovieComment(MovieCommentBean movieCommentBean) {

    }

    //关注电影
    @Override
    public void onMovieFollow(FollowMovieBean followMovieBean) {
        String message = followMovieBean.getMessage();
        if (message.equals("关注成功")) {
            Toast.makeText(this, "关注成功", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onCancelMovieFollow(CancelFollowMovieBean cancelFollowMovieBean) {
        String message = cancelFollowMovieBean.getMessage();
        if (message.equals("取消关注成功")) {
            Toast.makeText(this, "取消关注成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMovieCommentGreat(MovieCommentGreatBean movieCommentGreatBean) {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
