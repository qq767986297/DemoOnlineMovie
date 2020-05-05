package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.AddMovieCommentBean;

/**
 * Time: 2020/5/1
 * Author: 王冠华
 * Description:
 */
public interface IAddContract {
    interface IView extends IBaseView{
        void onAddMovieComment(AddMovieCommentBean addMovieCommentBean);
    }
    interface IPresenter{
        void getAddMovieComment(int movieId,String content,double score);
    }
    interface IModel{
        void onGetAddMovieComment(int movieId,String content,double score,IAddMovieCommentCallBack iAddMovieCommentCallBack);
        interface IAddMovieCommentCallBack{
            void onAddMovieComment(AddMovieCommentBean addMovieCommentBean);
        }
    }
}
