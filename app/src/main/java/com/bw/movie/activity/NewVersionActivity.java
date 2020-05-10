package com.bw.movie.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.FindNewVersionBean;
import com.bw.movie.bean.MineMovieCommentBean;
import com.bw.movie.bean.MineOrderBean;
import com.bw.movie.bean.SystemMsgBean;
import com.bw.movie.bean.SystemMsgChangeBean;
import com.bw.movie.bean.UserFeedBackBean;
import com.bw.movie.bean.UserFollowMovieBean;
import com.bw.movie.contract.IMineContract;
import com.bw.movie.presenter.MinePresenter;
import com.maning.updatelibrary.InstallUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewVersionActivity extends BaseActivity implements IMineContract.IView {


    @BindView(R.id.iv_mine_version_back)
    ImageView ivMineVersionBack;
    @BindView(R.id.tv_mine_version_num)
    TextView tvMineVersionNum;
    @BindView(R.id.bt_mine_version)
    Button bt;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_new)
    TextView tvNew;
    private String url;

    @Override
    protected BasePresenter initPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_new_version;
    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.iv_mine_version_back, R.id.bt_mine_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_version_back:
                finish();
                break;
            case R.id.bt_mine_version:
                down();
                break;
            default:
                break;
        }
    }
    private void down(){
        InstallUtils.with(this)
                .setApkUrl(url)
                .setCallBack(new InstallUtils.DownloadCallBack() {
                    @Override
                    public void onStart() {
                        Toast.makeText(NewVersionActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(final String s) {
                        InstallUtils.checkInstallPermission(NewVersionActivity.this, new InstallUtils.InstallPermissionCallBack() {
                            @Override
                            public void onGranted() {
                                install(s);
                            }

                            @Override
                            public void onDenied() {
                                AlertDialog dialog = new AlertDialog.Builder(NewVersionActivity.this)
                                        .setTitle("温馨提示")
                                        .setNegativeButton("取消", null)
                                        .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //打开设置页面
                                                InstallUtils.openInstallPermissionSetting(NewVersionActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                                    @Override
                                                    public void onGranted() {
                                                        install(s);
                                                    }

                                                    @Override
                                                    public void onDenied() {
                                                        //还是不允许咋搞？
                                                        Toast.makeText(NewVersionActivity.this, "不允许安装咋搞？强制更新就退出应用程序吧！", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        }).create();
                                        dialog.show();
                            }
                        });

                    }

                    @Override
                    public void onLoading(long l, long l1) {

                    }

                    @Override
                    public void onFail(Exception e) {

                    }

                    @Override
                    public void cancle() {

                    }
                }).startDownload();
    }
    private void install(String path){
        Log.i("wgh", "start install ");
        //安装APK
        InstallUtils.installAPK(this, path, new InstallUtils.InstallCallBack() {
            @Override
            public void onSuccess() {
                //onSuccess：表示系统的安装界面被打开
                //防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
                Log.i("wgh", "start install onSuccess");
                Toast.makeText(NewVersionActivity.this, "正在安装程序", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Exception e) {
                //安装出现异常，这里可以提示用用去用浏览器下载安装

                Log.i("wgh", "start install onFail");
                e.printStackTrace();
            }
        });
    }
    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IMineContract.IPresenter) {
            ((IMineContract.IPresenter) presenter).getFindNewVersion();
        }
    }

    @Override
    public void onFindNewVersion(FindNewVersionBean findNewVersionBean) {
        url = findNewVersionBean.getDownloadUrl();
        int flag = findNewVersionBean.getFlag();
        if(flag==2){
            bt.setVisibility(View.GONE);
        }
    }

    @Override
    public void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean) {

    }

    @Override
    public void onUserOrderMovie(MineOrderBean mineOrderBean) {

    }

    @Override
    public void onUserFeedBack(UserFeedBackBean userFeedBackBean) {

    }

    @Override
    public void onSystemMsg(SystemMsgBean systemMsgBean) {

    }

    @Override
    public void onSystemMsgChange(SystemMsgChangeBean systemMsgChangeBean) {

    }

    @Override
    public void onUserMovieComment(MineMovieCommentBean mineMovieCommentBean) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



}
