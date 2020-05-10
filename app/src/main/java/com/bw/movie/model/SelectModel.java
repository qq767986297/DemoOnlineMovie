package com.bw.movie.model;

import com.bw.movie.bean.FindCinemasInfoByRegion;
import com.bw.movie.bean.FindMovieScheduleBean;
import com.bw.movie.bean.FindSeatInfoBean;
import com.bw.movie.contract.ISelectContract;
import com.bw.movie.utils.RetrofiManger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/5/9
 * Author: 王冠华
 * Description:
 */
public class SelectModel implements ISelectContract.IModel {


    @Override
    public void onGetCinemaByRegion(int movieId, int regionId, int page, int count, final ICinemaByRegionCallBack iCinemaByRegionCallBack) {
        RetrofiManger.getInstance().getApis().getCinemasInfoByRegion(movieId, regionId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindCinemasInfoByRegion>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindCinemasInfoByRegion findCinemasInfoByRegion) {
                        if(iCinemaByRegionCallBack!=null){
                            iCinemaByRegionCallBack.onCinemaByRegion(findCinemasInfoByRegion);
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
    public void onGetShowSeat(int hallId, final IShowSeatCallBack iShowSeatCallBack) {
       RetrofiManger.getInstance().getApis().getfindSeatInfo(hallId)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<FindSeatInfoBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(FindSeatInfoBean findSeatInfoBean) {
                        if (iShowSeatCallBack!=null){
                            iShowSeatCallBack.onShowSeat(findSeatInfoBean);
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
    public void onGetFindMovieSchedule(int movieId, int cinemaId, final IFindMovieScheduleCallBack iFindMovieScheduleCallBack) {
        RetrofiManger.getInstance().getApis().getfindMovieSchedule(movieId, cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindMovieScheduleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindMovieScheduleBean findMovieScheduleBean) {
                        if(iFindMovieScheduleCallBack!=null){
                            iFindMovieScheduleCallBack.onFindMovieSchedule(findMovieScheduleBean);
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
