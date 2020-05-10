package com.bw.movie.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.MineCommentActivity;
import com.bw.movie.activity.MineFollowActivity;
import com.bw.movie.activity.MineOrderActivity;
import com.bw.movie.activity.MineSettingActivity;
import com.bw.movie.activity.SystemMsgActivity;
import com.bw.movie.activity.UserFeedBackActivity;
import com.bw.movie.activity.UserInfoActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.utils.SPUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Time: 2020/4/20
 * Author: 王冠华
 * Description:
 */
public class FragmentMine extends BaseFragment {
    @BindView(R.id.iv_mine_userinfo)
    ImageView ivMineUserinfo;
    @BindView(R.id.ticket)
    RelativeLayout ticket;
    @BindView(R.id.iv_mine_follow)
    ImageView ivMineFollow;
    @BindView(R.id.iv_mine_order)
    ImageView ivMineOrder;
    @BindView(R.id.iv_mine_record)
    ImageView ivMineRecord;
    @BindView(R.id.iv_mine_history)
    ImageView ivMineHistory;
    @BindView(R.id.iv_mine_comment)
    ImageView ivMineComment;
    @BindView(R.id.iv_mine_idea)
    ImageView ivMineIdea;
    @BindView(R.id.iv_mine_setting)
    ImageView ivMineSetting;
    Unbinder unbinder;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.iv_mine_systemmsg)
    ImageView ivMineSystemmsg;
    @BindView(R.id.iv_mine_head)
    SimpleDraweeView ivMineHead;
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
//        if(!EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().register(this);
//        }
    }

    //    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
//    public void init(LoginBean loginBean){
//        LoginBean.ResultBean result = loginBean.getResult();
//        LoginBean.ResultBean.UserInfoBean userInfo = result.getUserInfo();
//        String headPic = userInfo.getHeadPic();
//        String nickName = userInfo.getNickName();
//        Uri uri = Uri.parse(headPic);
//        ivMineHead.setImageURI(uri);
//        tvMineName.setText(nickName);
//
//    }
    @Override
    protected void initData() {
        String name = SPUtils.getString(getActivity(), "login", "NickName");
        String head = SPUtils.getString(getActivity(), "login", "headPic");
        Uri uri = Uri.parse(head);
        ImageRequest build = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true).build();
        AbstractDraweeController build1 = Fresco.newDraweeControllerBuilder().setImageRequest(build).build();
        ivMineHead.setController(build1);
        tvMineName.setText(name);
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

    @OnClick({R.id.iv_mine_userinfo, R.id.ticket, R.id.iv_mine_follow, R.id.iv_mine_order, R.id.iv_mine_record,
            R.id.iv_mine_history, R.id.iv_mine_comment, R.id.iv_mine_idea, R.id.iv_mine_setting, R.id.iv_mine_systemmsg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_userinfo:
                Intent intentInfo = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intentInfo);
                break;
            case R.id.ticket:
                break;
            case R.id.iv_mine_follow:
                Intent intent = new Intent(getActivity(), MineFollowActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_mine_order:
                Intent intent2 = new Intent(getActivity(), MineOrderActivity.class);
                startActivity(intent2);
                break;
            case R.id.iv_mine_record:
                break;
            case R.id.iv_mine_history:
                break;
            case R.id.iv_mine_comment:
                Intent intentMineComment = new Intent(getActivity(), MineCommentActivity.class);
                startActivity(intentMineComment);
                break;
            case R.id.iv_mine_idea:
                Intent intentFeedBack = new Intent(getActivity(), UserFeedBackActivity.class);
                startActivity(intentFeedBack);
                break;
            case R.id.iv_mine_setting:
                Intent intentSetting = new Intent(getActivity(), MineSettingActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.iv_mine_systemmsg:
                Intent intentSystem = new Intent(getActivity(), SystemMsgActivity.class);
                startActivity(intentSystem);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
