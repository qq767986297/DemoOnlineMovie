package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.bean.MovieCommentBean;
import com.bw.movie.bean.MovieCommentGreatBean;
import com.bw.movie.bean.MovieDetailBean;

/**
 * Time: 2020/4/24
 * Author: 王冠华
 * Description:
 */
public interface IDetailContract {
    interface IView extends IBaseView{
        void onDetailSuccess(MovieDetailBean movieDetailBean);
        void onMovieComment(MovieCommentBean movieCommentBean);
        void onMovieFollow(FollowMovieBean followMovieBean);
        void onCancelMovieFollow(CancelFollowMovieBean cancelFollowMovieBean);
        void onMovieCommentGreat(MovieCommentGreatBean movieCommentGreatBean);
    }
    interface IPresenter{
        void getDetail(int movieId);
        void getMovieComment(int movieId,int page,int count);
        void getMovieFollow(int movieId);
        void getCancelMovieFollow(int movieId);
        void getMovieCommentGreat(int commentId);
    }
    interface IModel{
        void onGetDetail(int movieId,IDetailCallBack iDetailCallBack);
        void onGetMovieComment(int movieId,int page,int count,IMovieCommentCallBack iMovieCommentCallBack);
        void onGetMovieFollow(int movieId,IMovieFollowCallBack iMovieFollowCallBack);
        void onGetCancelMovieFollow(int movieId,ICancelMovieFollowCallBack iCancelMovieFollowCallBack);
        void onGetMovieCommentGreat(int commentId,IMovieCommentGreatCallBack iMovieCommentGreatCallBack);
        interface IDetailCallBack{
            void onDetailSuccess(MovieDetailBean movieDetailBean);
        }
        interface IMovieCommentCallBack{
            void onMovieComment(MovieCommentBean movieCommentBean);
        }
        interface IMovieFollowCallBack{
            void onMovieFollow(FollowMovieBean followMovieBean);
        }
        interface ICancelMovieFollowCallBack{
            void onCancelMovieFollow(CancelFollowMovieBean cancelFollowMovieBean);
        }
        interface IMovieCommentGreatCallBack{
            void onMovieCommentGreat(MovieCommentGreatBean movieCommentGreatBean);
        }
    }
}
