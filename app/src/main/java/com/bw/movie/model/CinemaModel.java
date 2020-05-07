package com.bw.movie.model;

import com.bw.movie.bean.CinemaRecommendBean;
import com.bw.movie.contract.ICinemaContract;
import com.bw.movie.utils.RetrofiManger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/5/7
 * Author: 王冠华
 * Description:
 */
public class CinemaModel implements ICinemaContract.IModel {
    @Override
    public void onGetCinemaRecommend(int page, int count, final ICinemaRecommendCallBack iCinemaRecommendCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaRecommend(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaRecommendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaRecommendBean cinemaRecommendBean) {
                        if(iCinemaRecommendCallBack!=null){
                            iCinemaRecommendCallBack.onCinemaRecommend(cinemaRecommendBean);
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
