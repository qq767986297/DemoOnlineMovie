package com.bw.movie.model;

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
}
