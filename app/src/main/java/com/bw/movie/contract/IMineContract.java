package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.UserFollowMovieBean;

/**
 * Time: 2020/4/28
 * Author: 王冠华
 * Description:
 */
public interface IMineContract {
    interface IView extends IBaseView{
        void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean);
    }
    interface IPresenter{
        void getUserFollowMovie(int page,int count);
    }
    interface IModel{
        void onGetUserFollowMovie(int page,int count,IUserFollowMovieCallBack iUserFollowMovieCallBack);
        interface IUserFollowMovieCallBack{
            void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean);
        }
    }
}
