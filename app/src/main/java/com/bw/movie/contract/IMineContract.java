package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.FindNewVersionBean;
import com.bw.movie.bean.MineMovieCommentBean;
import com.bw.movie.bean.MineOrderBean;
import com.bw.movie.bean.SystemMsgBean;
import com.bw.movie.bean.SystemMsgChangeBean;
import com.bw.movie.bean.UserFeedBackBean;
import com.bw.movie.bean.UserFollowMovieBean;

/**
 * Time: 2020/4/28
 * Author: 王冠华
 * Description:
 */
public interface IMineContract {
    interface IView extends IBaseView{
        void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean);
        void onUserOrderMovie(MineOrderBean mineOrderBean);
        void onUserFeedBack(UserFeedBackBean userFeedBackBean);
        void onSystemMsg(SystemMsgBean systemMsgBean);
        void onSystemMsgChange(SystemMsgChangeBean systemMsgChangeBean);
        void onUserMovieComment(MineMovieCommentBean mineMovieCommentBean);
        void onFindNewVersion(FindNewVersionBean findNewVersionBean);
    }
    interface IPresenter{
        void getUserFollowMovie(int page,int count);
        void getUserOrderMovie();
        void getUserFeedBack(String content);
        void getSystemMsg(int page,int count);
        void getSystemMsgChange(int id);
        void getUserMovieComment(int page,int count);
        void getFindNewVersion();
    }
    interface IModel{
        void onGetUserFollowMovie(int page,int count,IUserFollowMovieCallBack iUserFollowMovieCallBack);
        void onGetUserOrderMovie(IUserOrderCallBack iUserOrderCallBack);
        void onGetUserFeedBack(String content,IUserFeedBackCallBack iUserFeedBackCallBack);
        void onGetSystemMsg(int page,int count,ISystemMsgCallBack iSystemMsgCallBack);
        void onGetSystemMsgChange(int id,ISystemMsgChangeCallBack iSystemMsgChangeCallBack);
        void onGetUserMovieComment(int page,int count,IUserMovieCommentICallBack iUserMovieCommentICallBack);
        void onGetFindNewVersion(IFindNewVersionCallBack iFindNewVersionCallBack);
        interface IUserFollowMovieCallBack{
            void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean);
        }
        interface IUserOrderCallBack{
            void onUserOrderMovie(MineOrderBean mineOrderBean);
        }
        interface IUserFeedBackCallBack{
            void onUserFeedBack(UserFeedBackBean userFeedBackBean);
        }
        interface ISystemMsgCallBack{
            void onSystemMsg(SystemMsgBean systemMsgBean);
        }
        interface ISystemMsgChangeCallBack{
            void onSystemMsgChange(SystemMsgChangeBean systemMsgChangeBean);
        }
        interface IUserMovieCommentICallBack{
            void onUserMovieComment(MineMovieCommentBean mineMovieCommentBean);
        }
        interface IFindNewVersionCallBack{
            void onFindNewVersion(FindNewVersionBean findNewVersionBean);
        }
    }
}
