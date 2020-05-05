package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.bean.MovieCommentBean;
import com.bw.movie.bean.MovieCommentGreatBean;
import com.bw.movie.bean.MovieDetailBean;
import com.bw.movie.contract.IDetailContract;
import com.bw.movie.model.DetailModel;

/**
 * Time: 2020/4/24
 * Author: 王冠华
 * Description:
 */
public class DetailPresenter extends BasePresenter implements IDetailContract.IPresenter {

    private DetailModel model;

    public DetailPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new DetailModel();

    }

    @Override
    public void getDetail(int movieId) {
        model.onGetDetail(movieId, new IDetailContract.IModel.IDetailCallBack() {
            @Override
            public void onDetailSuccess(MovieDetailBean movieDetailBean) {
                IBaseView view = getView();
                if(view instanceof IDetailContract.IView){
                    IDetailContract.IView iView= (IDetailContract.IView) view;
                    iView.onDetailSuccess(movieDetailBean);
                }
            }
        });
    }

    @Override
    public void getMovieComment(int movieId, int page, int count) {
        model.onGetMovieComment(movieId, page, count, new IDetailContract.IModel.IMovieCommentCallBack() {
            @Override
            public void onMovieComment(MovieCommentBean movieCommentBean) {
                IBaseView view = getView();
                if(view instanceof IDetailContract.IView){
                    IDetailContract.IView iView= (IDetailContract.IView) view;
                    iView.onMovieComment(movieCommentBean);
                }
            }
        });
    }

    @Override
    public void getMovieFollow(int movieId) {
        model.onGetMovieFollow(movieId, new IDetailContract.IModel.IMovieFollowCallBack() {
            @Override
            public void onMovieFollow(FollowMovieBean followMovieBean) {
                IBaseView view = getView();
                if(view instanceof IDetailContract.IView){
                    IDetailContract.IView iView= (IDetailContract.IView) view;
                    iView.onMovieFollow(followMovieBean);
                }
            }
        });
    }

    @Override
    public void getCancelMovieFollow(int movieId) {
        model.onGetCancelMovieFollow(movieId, new IDetailContract.IModel.ICancelMovieFollowCallBack() {
            @Override
            public void onCancelMovieFollow(CancelFollowMovieBean cancelFollowMovieBean) {
                IBaseView view = getView();
                if(view instanceof IDetailContract.IView){
                    IDetailContract.IView iView= (IDetailContract.IView) view;
                    iView.onCancelMovieFollow(cancelFollowMovieBean);
                }
            }
        });
    }

    @Override
    public void getMovieCommentGreat(int commentId) {
        model.onGetMovieCommentGreat(commentId, new IDetailContract.IModel.IMovieCommentGreatCallBack() {
            @Override
            public void onMovieCommentGreat(MovieCommentGreatBean movieCommentGreatBean) {
                IBaseView view = getView();
                if(view instanceof IDetailContract.IView){
                    IDetailContract.IView iView= (IDetailContract.IView) view;
                    iView.onMovieCommentGreat(movieCommentGreatBean);
                }
            }
        });
    }
}
