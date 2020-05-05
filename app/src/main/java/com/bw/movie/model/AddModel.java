package com.bw.movie.model;

import com.bw.movie.bean.AddMovieCommentBean;
import com.bw.movie.contract.IAddContract;
import com.bw.movie.utils.RetrofiManger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/5/1
 * Author: 王冠华
 * Description:
 */
public class AddModel implements IAddContract.IModel {
    @Override
    public void onGetAddMovieComment(int movieId, String content, double score, final IAddMovieCommentCallBack iAddMovieCommentCallBack) {
        RetrofiManger.getInstance().getApis().getAddMovieComment(movieId, content, score)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddMovieCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddMovieCommentBean addMovieCommentBean) {
                        if(iAddMovieCommentCallBack!=null){
                            iAddMovieCommentCallBack.onAddMovieComment(addMovieCommentBean);
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
