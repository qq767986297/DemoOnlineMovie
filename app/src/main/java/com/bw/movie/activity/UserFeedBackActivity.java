package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MineMovieCommentBean;
import com.bw.movie.bean.MineOrderBean;
import com.bw.movie.bean.SystemMsgBean;
import com.bw.movie.bean.SystemMsgChangeBean;
import com.bw.movie.bean.UserFeedBackBean;
import com.bw.movie.bean.UserFollowMovieBean;
import com.bw.movie.contract.IMineContract;
import com.bw.movie.presenter.MinePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFeedBackActivity extends BaseActivity implements IMineContract.IView {

    @BindView(R.id.iv_mine_feedback_back)
    ImageView iv;
    @BindView(R.id.et_feedback)
    EditText et;
    @BindView(R.id.bt_mine_feedback)
    Button bt;

    @Override
    protected BasePresenter initPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_feed_back;
    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.iv_mine_feedback_back, R.id.bt_mine_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_feedback_back:
                finish();
                break;
            case R.id.bt_mine_feedback:
                BasePresenter presenter = getPresenter();
                if(presenter instanceof IMineContract.IPresenter){
                    String content = et.getText().toString();
                    ((IMineContract.IPresenter)presenter).getUserFeedBack(content);
                }
                break;
                default:
                    break;
        }
    }
    @Override
    protected void initData() {

    }

    @Override
    public void onUserFeedBack(UserFeedBackBean userFeedBackBean) {
        String message = userFeedBackBean.getMessage();
        if (message.equals("反馈成功")) {
            Intent intent = new Intent(UserFeedBackActivity.this, FeedBackSuccessActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
        }
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
    public void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean) {

    }

    @Override
    public void onUserOrderMovie(MineOrderBean mineOrderBean) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
