package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.FindNewVersionBean;
import com.bw.movie.bean.MineMovieCommentBean;
import com.bw.movie.bean.MineOrderBean;
import com.bw.movie.bean.SystemMsgBean;
import com.bw.movie.bean.SystemMsgChangeBean;
import com.bw.movie.bean.UserFeedBackBean;
import com.bw.movie.bean.UserFollowMovieBean;
import com.bw.movie.contract.IMineContract;
import com.bw.movie.model.MineModel;

/**
 * Time: 2020/4/28
 * Author: 王冠华
 * Description:
 */
public class MinePresenter extends BasePresenter implements IMineContract.IPresenter {


    private MineModel model;

    public MinePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new MineModel();
    }

    @Override
    public void getUserFollowMovie(int page, int count) {
        model.onGetUserFollowMovie(page, count, new IMineContract.IModel.IUserFollowMovieCallBack() {
            @Override
            public void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean) {
                IBaseView view = getView();
                if(view instanceof IMineContract.IView){
                    IMineContract.IView iView= (IMineContract.IView) view;
                    iView.onUserFollowMovie(userFollowMovieBean);
                }
            }
        });
    }

    @Override
    public void getUserOrderMovie() {
        model.onGetUserOrderMovie(new IMineContract.IModel.IUserOrderCallBack() {
            @Override
            public void onUserOrderMovie(MineOrderBean mineOrderBean) {
                IBaseView view = getView();
                if(view instanceof IMineContract.IView){
                    IMineContract.IView iView= (IMineContract.IView) view;
                    iView.onUserOrderMovie(mineOrderBean);
                }
            }
        });
    }

    @Override
    public void getUserFeedBack(String content) {
        model.onGetUserFeedBack(content, new IMineContract.IModel.IUserFeedBackCallBack() {
            @Override
            public void onUserFeedBack(UserFeedBackBean userFeedBackBean) {
                IBaseView view = getView();
                if(view instanceof IMineContract.IView){
                    IMineContract.IView iView= (IMineContract.IView) view;
                    iView.onUserFeedBack(userFeedBackBean);
                }
            }
        });
    }

    @Override
    public void getSystemMsg(int page, int count) {
        model.onGetSystemMsg(page, count, new IMineContract.IModel.ISystemMsgCallBack() {
            @Override
            public void onSystemMsg(SystemMsgBean systemMsgBean) {
                IBaseView view = getView();
                if(view instanceof IMineContract.IView){
                    IMineContract.IView iView= (IMineContract.IView) view;
                    iView.onSystemMsg(systemMsgBean);
                }
            }
        });
    }

    @Override
    public void getSystemMsgChange(int id) {
      model.onGetSystemMsgChange(id, new IMineContract.IModel.ISystemMsgChangeCallBack() {
          @Override
          public void onSystemMsgChange(SystemMsgChangeBean systemMsgChangeBean) {
              IBaseView view = getView();
              if(view instanceof IMineContract.IView){
                  IMineContract.IView iView= (IMineContract.IView) view;
                  iView.onSystemMsgChange(systemMsgChangeBean);
              }
          }
      });
    }

    @Override
    public void getUserMovieComment(int page, int count) {
        model.onGetUserMovieComment(page, count, new IMineContract.IModel.IUserMovieCommentICallBack() {
            @Override
            public void onUserMovieComment(MineMovieCommentBean mineMovieCommentBean) {
                IBaseView view = getView();
                if(view instanceof IMineContract.IView){
                    Log.i("TTT","M层");
                    IMineContract.IView iView= (IMineContract.IView) view;
                    iView.onUserMovieComment(mineMovieCommentBean);
                }
            }
        });
    }

    @Override
    public void getFindNewVersion() {
        model.onGetFindNewVersion(new IMineContract.IModel.IFindNewVersionCallBack() {
            @Override
            public void onFindNewVersion(FindNewVersionBean findNewVersionBean) {
                IBaseView view = getView();
                if(view instanceof IMineContract.IView){

                    IMineContract.IView iView= (IMineContract.IView) view;
                    iView.onFindNewVersion(findNewVersionBean);
                }
            }
        });
    }
}
