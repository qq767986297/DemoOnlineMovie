package com.bw.movie.model;

import com.bw.movie.bean.HomeBannerBean;
import com.bw.movie.bean.HomeHotMovieBean;
import com.bw.movie.bean.HomeReleaseMovieBean;
import com.bw.movie.bean.HomeSearchMovieBean;
import com.bw.movie.bean.HomeSoonMovieBean;
import com.bw.movie.bean.MovieReserveBean;
import com.bw.movie.contract.HomeContract;
import com.bw.movie.utils.RetrofiManger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/4/22
 * Author: 王冠华
 * Description:
 */
public class HomeModel implements HomeContract.IHomeModel {
    @Override
    public void onGetBanner(final IBannerCallBack iBannerCallBack) {
        RetrofiManger.getInstance().getApis().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        if(iBannerCallBack!=null){
                            iBannerCallBack.onBannerSuccess(homeBannerBean);
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
    public void onGetHomeReleaseMovie(int page, int count, final IReleaseMovieCallBack iReleaseMovieCallBack) {
        RetrofiManger.getInstance().getApis().getReleaseMovie(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeReleaseMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeReleaseMovieBean homeReleaseMovieBean) {
                        if(iReleaseMovieCallBack!=null){
                            iReleaseMovieCallBack.onHomeReleaseMovieSuccess(homeReleaseMovieBean);
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
    public void onGetHomeSoonMovie(int page, int count, final ISoonMovieCallBack iSoonMovieCallBack) {
        RetrofiManger.getInstance().getApis().getSoonMovie(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeSoonMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeSoonMovieBean homeSoonMovieBean) {
                        if(iSoonMovieCallBack!=null){
                            iSoonMovieCallBack.onHomeSoonMovieSuccess(homeSoonMovieBean);
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
    public void onGetHomeHotMovie(int page, int count, final IHotMovieCallBack iHotMovieCallBack) {
        RetrofiManger.getInstance().getApis().getHotMovie(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeHotMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeHotMovieBean homeHotMovieBean) {
                        if(iHotMovieCallBack!=null){
                            iHotMovieCallBack.onHomeHotMovieSuccess(homeHotMovieBean);
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
    public void onGetHomeSerachMovie(String keyword, int page, int count, final ISerachMovieCallBack iSerachMovieCallBack) {
        RetrofiManger.getInstance().getApis().getHomeSerachMovie(keyword, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeSearchMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeSearchMovieBean homeSearchMovieBean) {
                        if(iSerachMovieCallBack!=null){
                            iSerachMovieCallBack.onHomeSerachMovieSuccess(homeSearchMovieBean);
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
    public void onGetMovieReserve(int movieId, final IMovieReseeveICallBack iMovieReseeveICallBack) {
        RetrofiManger.getInstance().getApis().getMovieReserve(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieReserveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieReserveBean movieReserveBean) {
                        if(iMovieReseeveICallBack!=null){
                            iMovieReseeveICallBack.onMovieReserve(movieReserveBean);
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
