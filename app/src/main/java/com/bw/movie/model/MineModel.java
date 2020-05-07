package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.bean.MineMovieCommentBean;
import com.bw.movie.bean.MineOrderBean;
import com.bw.movie.bean.SystemMsgBean;
import com.bw.movie.bean.SystemMsgChangeBean;
import com.bw.movie.bean.UserFeedBackBean;
import com.bw.movie.bean.UserFollowMovieBean;
import com.bw.movie.contract.IMineContract;
import com.bw.movie.utils.RetrofiManger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/4/28
 * Author: 王冠华
 * Description:
 */
public class MineModel implements IMineContract.IModel {

    @Override
    public void onGetUserFollowMovie(int page, int count, final IUserFollowMovieCallBack iUserFollowMovieCallBack) {
        RetrofiManger.getInstance().getApis().getUserFollowMovie(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserFollowMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserFollowMovieBean userFollowMovieBean) {
                        if(iUserFollowMovieCallBack!=null){
                            iUserFollowMovieCallBack.onUserFollowMovie(userFollowMovieBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetUserOrderMovie(final IUserOrderCallBack iUserOrderCallBack) {
        RetrofiManger.getInstance().getApis().getUserOrder()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MineOrderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MineOrderBean mineOrderBean) {
                        if(iUserOrderCallBack!=null){
                            iUserOrderCallBack.onUserOrderMovie(mineOrderBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetUserFeedBack(String content, final IUserFeedBackCallBack iUserFeedBackCallBack) {
        RetrofiManger.getInstance().getApis().getFeedBack(content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserFeedBackBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserFeedBackBean userFeedBackBean) {
                        if(iUserFeedBackCallBack!=null){
                            iUserFeedBackCallBack.onUserFeedBack(userFeedBackBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetSystemMsg(int page, int count, final ISystemMsgCallBack iSystemMsgCallBack) {
        RetrofiManger.getInstance().getApis().getSystemMsg(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SystemMsgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SystemMsgBean systemMsgBean) {
                        if(iSystemMsgCallBack!=null){
                            iSystemMsgCallBack.onSystemMsg(systemMsgBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetSystemMsgChange(int id, final ISystemMsgChangeCallBack iSystemMsgChangeCallBack) {
        RetrofiManger.getInstance().getApis().getSystemMsgChange(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SystemMsgChangeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SystemMsgChangeBean systemMsgChangeBean) {
                        if(iSystemMsgChangeCallBack!=null){
                            iSystemMsgChangeCallBack.onSystemMsgChange(systemMsgChangeBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetUserMovieComment(int page, int count, final IUserMovieCommentICallBack iUserMovieCommentICallBack) {
        RetrofiManger.getInstance().getApis().getMineMovieComment(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MineMovieCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MineMovieCommentBean mineMovieCommentBean) {
                        if(iUserMovieCommentICallBack!=null){
                            iUserMovieCommentICallBack.onUserMovieComment(mineMovieCommentBean);
                        }
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
