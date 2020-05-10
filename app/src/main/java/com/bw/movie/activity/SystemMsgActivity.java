package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MineSystemMsgAdapter;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemMsgActivity extends BaseActivity implements IMineContract.IView {


    @BindView(R.id.iv_systemmsg_back)
    ImageView iv;
    @BindView(R.id.rv_systemmsg)
    RecyclerView rv;

    @Override
    protected BasePresenter initPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_system_msg;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(!EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().register(this);
//        }
    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof IMineContract.IPresenter){
            ((IMineContract.IPresenter)presenter).getSystemMsg(1,10);

        }
    }
//    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
//    public void init(Integer id){
//        BasePresenter presenter = getPresenter();
//        ((IMineContract.IPresenter)presenter).getSystemMsgChange(id);
//    }
    @Override
    public void onSystemMsg(SystemMsgBean systemMsgBean) {
        List<SystemMsgBean.ResultBean> list = systemMsgBean.getResult();
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        final MineSystemMsgAdapter adapter = new MineSystemMsgAdapter(this, list);
        rv.setAdapter(adapter);
        adapter.onChange(new MineSystemMsgAdapter.onClick() {
            @Override
            public void setClick(int i) {
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onSystemMsgChange(SystemMsgChangeBean systemMsgChangeBean) {
//        String message = systemMsgChangeBean.getMessage();
//        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserMovieComment(MineMovieCommentBean mineMovieCommentBean) {

    }

    @Override
    public void onFindNewVersion(FindNewVersionBean findNewVersionBean) {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_systemmsg_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
