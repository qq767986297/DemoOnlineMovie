package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
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

/**
 * Time: 2020/5/7
 * Author: 王冠华
 * Description:
 */
public interface ICinemaContract {
    interface IView extends IBaseView{
        void onCinemaRecommend(CinemaRecommendBean cinemaRecommendBean);
        void onCinemaNearby(CinemaNearbyBean cinemaNearbyBean);
        void onCinemaRegion(CinemaRegionBean cinemaRegionBean);
        void onCinemaLink(CinemaLinkBean cinemaLinkBean);
        void onCinemaDetail(CinemaDetailBean cinemaDetailBean);
        void onCinemaDetailComment(CinemaDetailCommentBean cinemaDetailCommentBean);
        void onCinemaFollow(CinemaFollowBean cinemaFollowBean);
        void onCinemaCanleFollow(CinemaCanelFollowBean cinemaCanelFollowBean);
        void onCinemaCommentGood(CinemaCommentGoodBean cinemaCommentGoodBean);
        void onCinemaSchedu(CinemaScheduleListBean cinemaScheduleListBean);
        void onCinemaData(FindDataBean findDataBean);
    }
    interface IPresenter{
        void getCinemaRecommend(int page,int count);
        void getCinemaNearby(String longitude ,String latitude, int page,int count);
        void getCinemaRegion();
        void getCinemaLink(int regionId);
        void getCinemaDetail(int cinemaId);
        void getCinemaDetailComment(int cinemaId, int page,int count);
        void getCinemaFollow(int cinemaId);
        void getCinemaCanelFollow(int cinemaId);
        void getCinemaCommentGood(int commentId);
        void getCinemaSchedu(int cinemaId, int page,int count);
        void getCinemaData();
    }
    interface IModel{
        void onGetCinemaRecommend(int page,int count,ICinemaRecommendCallBack iCinemaRecommendCallBack);
        void onGetCinemaNearby(String longitude ,String latitude, int page,int count,ICinemaNearbyCallBack iCinemaNearbyCallBack);
        void onGetCinemaRegion(ICinemaRegionCallBack iCinemaRegionCallBack);
        void onGetCinemaLink(int regionId,ICinemaLinkCallBack iCinemaLinkCallBack);
        void onGetCinemaDetail(int cinemaId,ICinemaDetailCallBack iCinemaDetailCallBack);
        void onGetCinemaDetailComment(int cinemaId, int page,int count,ICinemaDtailCommentCallBack iCinemaDtailCommentCallBack);
        void onGetCinemaFollow(int cinemaId,ICinemaFollowCallBack iCinemaFollowCallBack);
        void onGetCinemaCanelFollow(int cinemaId,ICinemaCanelFollowCallBack iCinemaCanelFollowCallBack);
        void onGetCinemaCommentGood(int commentId,ICinemaCommentGoodCallBack iCinemaCommentGoodCallBack);
        void onGetCinemaSchedu(int cinemaId, int page,int count,ICinemaScheduCallBack iCinemaScheduCallBack);
        void onGetCinemaData(ICinemaDataCallBack iCinemaDataCallBack);
        interface ICinemaRecommendCallBack{
            void onCinemaRecommend(CinemaRecommendBean cinemaRecommendBean);
        }
        interface ICinemaNearbyCallBack{
            void onCinemaNearby(CinemaNearbyBean cinemaNearbyBean);
        }
        interface ICinemaRegionCallBack{
            void onCinemaRegion(CinemaRegionBean cinemaRegionBean);
        }
        interface ICinemaLinkCallBack{
            void onCinemaLink(CinemaLinkBean cinemaLinkBean);
        }
        interface ICinemaDetailCallBack{
            void onCinemaDetail(CinemaDetailBean cinemaDetailBean);
        }
        interface ICinemaDtailCommentCallBack{
            void onCinemaDetailComment(CinemaDetailCommentBean cinemaDetailCommentBean);
        }
        interface ICinemaFollowCallBack{
            void onCinemaFollow(CinemaFollowBean cinemaFollowBean);
        }
        interface ICinemaCanelFollowCallBack{
            void onCinemaCanleFollow(CinemaCanelFollowBean cinemaCanelFollowBean);
        }
        interface ICinemaCommentGoodCallBack{
            void onCinemaCommentGood(CinemaCommentGoodBean cinemaCommentGoodBean);
        }
        interface ICinemaScheduCallBack{
            void onCinemaSchedu(CinemaScheduleListBean cinemaScheduleListBean);
        }
        interface ICinemaDataCallBack{
            void onCinemaData(FindDataBean findDataBean);
        }
    }
}
