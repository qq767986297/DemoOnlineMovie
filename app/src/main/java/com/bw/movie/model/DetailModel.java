package com.bw.movie.model;

import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.bean.MovieCommentBean;
import com.bw.movie.bean.MovieCommentGreatBean;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.contract.IDetailContract;
import com.bw.movie.utils.RetrofiManger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/4/24
 * Author: 王冠华
 * Description:
 */
public class DetailModel implements IDetailContract.IModel {
    @Override
    public void onGetDetail(int movieId, final IDetailCallBack iDetailCallBack) {
        RetrofiManger.getInstance().getApis().getDetail(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetailBean movieDetailBean) {
                        if(iDetailCallBack!=null){
                            iDetailCallBack.onDetailSuccess(movieDetailBean);
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
    public void onGetMovieComment(int movieId, int page, int count, final IMovieCommentCallBack iMovieCommentCallBack) {
        RetrofiManger.getInstance().getApis().getMovieComment(movieId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieCommentBean movieCommentBean) {
                        if(iMovieCommentCallBack!=null){
                            iMovieCommentCallBack.onMovieComment(movieCommentBean);
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
    public void onGetMovieFollow(int movieId, final IMovieFollowCallBack iMovieFollowCallBack) {
        RetrofiManger.getInstance().getApis().getFollowMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowMovieBean followMovieBean) {
                        if(iMovieFollowCallBack!=null){
                            iMovieFollowCallBack.onMovieFollow(followMovieBean);
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
    public void onGetCancelMovieFollow(int movieId, final ICancelMovieFollowCallBack iCancelMovieFollowCallBack) {
        RetrofiManger.getInstance().getApis().getCancelFollowMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CancelFollowMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CancelFollowMovieBean cancelFollowMovieBean) {
                            if(iCancelMovieFollowCallBack!=null){
                                iCancelMovieFollowCallBack.onCancelMovieFollow(cancelFollowMovieBean);
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
    public void onGetMovieCommentGreat(int commentId, final IMovieCommentGreatCallBack iMovieCommentGreatCallBack) {
        RetrofiManger.getInstance().getApis().getMovieCommentGreat(commentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieCommentGreatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieCommentGreatBean movieCommentGreatBean) {
                        if(iMovieCommentGreatCallBack!=null){
                            iMovieCommentGreatCallBack.onMovieCommentGreat(movieCommentGreatBean);
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
