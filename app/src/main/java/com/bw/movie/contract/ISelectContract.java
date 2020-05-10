package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.FindCinemasInfoByRegion;
import com.bw.movie.bean.FindMovieScheduleBean;
import com.bw.movie.bean.FindSeatInfoBean;

import retrofit2.http.Query;

/**
 * Time: 2020/5/9
 * Author: 王冠华
 * Description:
 */
public interface ISelectContract {
    interface IView extends IBaseView{
        void onCinemaByRegion(FindCinemasInfoByRegion findCinemasInfoByRegion);
        void onShowSeat(FindSeatInfoBean findSeatInfoBean);
        void onFindMovieSchedule(FindMovieScheduleBean findMovieScheduleBean);
    }
    interface IPresenter{
        void getCinemaByRegion(int movieId, int regionId, int page,int count);
        void getShowSeat(int hallId);
        void getFindMovieSchedule(int movieId,int cinemaId);
    }
    interface IModel{
        void onGetCinemaByRegion(int movieId, int regionId, int page,int count,ICinemaByRegionCallBack iCinemaByRegionCallBack);
        void onGetShowSeat(int hallId,IShowSeatCallBack iShowSeatCallBack);
        void onGetFindMovieSchedule(int movieId,int cinemaId,IFindMovieScheduleCallBack iFindMovieScheduleCallBack);
        interface ICinemaByRegionCallBack{
            void onCinemaByRegion(FindCinemasInfoByRegion findCinemasInfoByRegion);
        }
        interface IShowSeatCallBack{
            void onShowSeat(FindSeatInfoBean findSeatInfoBean);
        }
        interface IFindMovieScheduleCallBack{
            void onFindMovieSchedule(FindMovieScheduleBean findMovieScheduleBean);
        }
    }
}
