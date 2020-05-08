package com.bw.movie.model;

import com.bw.movie.bean.CinemaCanelFollowBean;
import com.bw.movie.bean.CinemaCommentGoodBean;
import com.bw.movie.bean.CinemaDetailBean;
import com.bw.movie.bean.CinemaDetailCommentBean;
import com.bw.movie.bean.CinemaFollowBean;
import com.bw.movie.bean.CinemaLinkBean;
import com.bw.movie.bean.CinemaNearbyBean;
import com.bw.movie.bean.CinemaRecommendBean;
import com.bw.movie.bean.CinemaRegionBean;
import com.bw.movie.bean.CinemaScheduleListBean;
import com.bw.movie.bean.FindDataBean;
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

    @Override
    public void onGetCinemaNearby(String longitude, String latitude, int page, int count, final ICinemaNearbyCallBack iCinemaNearbyCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaNearby(longitude, latitude, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaNearbyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaNearbyBean cinemaNearbyBean) {
                        if(iCinemaNearbyCallBack!=null){
                            iCinemaNearbyCallBack.onCinemaNearby(cinemaNearbyBean);
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
    public void onGetCinemaRegion(final ICinemaRegionCallBack iCinemaRegionCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaRegion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaRegionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaRegionBean cinemaRegionBean) {
                        if(iCinemaRegionCallBack!=null){
                            iCinemaRegionCallBack.onCinemaRegion(cinemaRegionBean);
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
    public void onGetCinemaLink(int regionId, final ICinemaLinkCallBack iCinemaLinkCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaLink(regionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaLinkBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaLinkBean cinemaLinkBean) {
                        if(iCinemaLinkCallBack!=null){
                            iCinemaLinkCallBack.onCinemaLink(cinemaLinkBean);
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
    public void onGetCinemaDetail(int cinemaId, final ICinemaDetailCallBack iCinemaDetailCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaDetail(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaDetailBean cinemaDetailBean) {
                        if(iCinemaDetailCallBack!=null){
                            iCinemaDetailCallBack.onCinemaDetail(cinemaDetailBean);
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
    public void onGetCinemaDetailComment(int cinemaId, int page, int count, final ICinemaDtailCommentCallBack iCinemaDtailCommentCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaDetailComment(cinemaId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaDetailCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaDetailCommentBean cinemaDetailCommentBean) {
                        if(iCinemaDtailCommentCallBack!=null){
                            iCinemaDtailCommentCallBack.onCinemaDetailComment(cinemaDetailCommentBean);
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
    public void onGetCinemaFollow(int cinemaId, final ICinemaFollowCallBack iCinemaFollowCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaFollow(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaFollowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaFollowBean cinemaFollowBean) {
                        if(iCinemaFollowCallBack!=null){
                            iCinemaFollowCallBack.onCinemaFollow(cinemaFollowBean);
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
    public void onGetCinemaCanelFollow(int cinemaId, final ICinemaCanelFollowCallBack iCinemaCanelFollowCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaCanelFollow(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaCanelFollowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaCanelFollowBean cinemaCanelFollowBean) {
                        if(iCinemaCanelFollowCallBack!=null){
                            iCinemaCanelFollowCallBack.onCinemaCanleFollow(cinemaCanelFollowBean);
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
    public void onGetCinemaCommentGood(int commentId, final ICinemaCommentGoodCallBack iCinemaCommentGoodCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaGood(commentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaCommentGoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaCommentGoodBean cinemaCommentGoodBean) {
                        if(iCinemaCommentGoodCallBack!=null){
                            iCinemaCommentGoodCallBack.onCinemaCommentGood(cinemaCommentGoodBean);
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
    public void onGetCinemaSchedu(int cinemaId, int page, int count, final ICinemaScheduCallBack iCinemaScheduCallBack) {
        RetrofiManger.getInstance().getApis().getCinemaSchedu(cinemaId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaScheduleListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaScheduleListBean cinemaScheduleListBean) {
                        if(iCinemaScheduCallBack!=null){
                            iCinemaScheduCallBack.onCinemaSchedu(cinemaScheduleListBean);
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
    public void onGetCinemaData(final ICinemaDataCallBack iCinemaDataCallBack) {
        RetrofiManger.getInstance().getApis().getFindData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindDataBean findDataBean) {
                        if(iCinemaDataCallBack!=null){
                            iCinemaDataCallBack.onCinemaData(findDataBean);
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
