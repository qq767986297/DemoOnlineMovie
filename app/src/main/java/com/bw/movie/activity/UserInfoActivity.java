package com.bw.movie.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.UpLoadHeadPicBean;
import com.bw.movie.utils.RetrofiManger;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wildma.pictureselector.PictureSelector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

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
                PictureSelector
                        .create(this,PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true,200,200,1,1);
                break;
            case R.id.tv_mine_userinfo_name:
                break;
            case R.id.tv_mine_userinfo_time:
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==PictureSelector.SELECT_REQUEST_CODE){
            if (data != null) {
                String stringExtra = data.getStringExtra(PictureSelector.PICTURE_PATH);
                File file = new File(stringExtra);
                ArrayList<File> files = new ArrayList<>();
                files.add(file);
                HashMap<String, String> map = new HashMap<>();
                RequestBody requsetBody = RetrofiManger.getInstance().getRequsetBody(files, map);
                RetrofiManger.getInstance().getApis().getUpLoadHeadPicBean(requsetBody)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UpLoadHeadPicBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(UpLoadHeadPicBean upLoadHeadPicBean) {
                                Toast.makeText(UserInfoActivity.this, ""+upLoadHeadPicBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
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
