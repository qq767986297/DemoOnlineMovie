package com.bw.movie.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.LoginBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity {


    @BindView(R.id.iv_mine_userinfo_back)
    ImageView ivMineUserinfoBack;
    @BindView(R.id.iv_mine_userinfo)
    SimpleDraweeView ivMineUserinfo;
    @BindView(R.id.tv_mine_userinfo_name)
    TextView tvMineUserinfoName;
    @BindView(R.id.tv_mine_userinfo_sex)
    TextView tvMineUserinfoSex;
    @BindView(R.id.tv_mine_userinfo_time)
    TextView tvMineUserinfoTime;
    @BindView(R.id.tv_mine_userinfo_phone)
    TextView tvMineUserinfoPhone;
    @BindView(R.id.tv_mine_userinfo_eamil)
    TextView tvMineUserinfoEamil;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
    @OnClick({R.id.iv_mine_userinfo_back, R.id.iv_mine_userinfo, R.id.tv_mine_userinfo_name, R.id.tv_mine_userinfo_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_userinfo_back:
                finish();
                break;
            case R.id.iv_mine_userinfo:
                break;
            case R.id.tv_mine_userinfo_name:
                break;
            case R.id.tv_mine_userinfo_time:
                break;
                default:
                    break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void init(LoginBean loginBean) {
        LoginBean.ResultBean result = loginBean.getResult();
        LoginBean.ResultBean.UserInfoBean info = result.getUserInfo();
        String headPic = info.getHeadPic();
        Uri uri = Uri.parse(headPic);
        ivMineUserinfo.setImageURI(uri);
        tvMineUserinfoName.setText(info.getNickName());
        Date date = new Date(info.getLastLoginTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        tvMineUserinfoTime.setText(format);
        tvMineUserinfoPhone.setText("16824908716");
        tvMineUserinfoEamil.setText(info.getEmail());
        if(info.getSex()==1){
            tvMineUserinfoSex.setText("男");
        }else {
            tvMineUserinfoSex.setText("女");
        }

    }

    @Override
    protected void initData() {

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


}
