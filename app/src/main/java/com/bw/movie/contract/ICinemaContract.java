package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.CinemaRecommendBean;

/**
 * Time: 2020/5/7
 * Author: 王冠华
 * Description:
 */
public interface ICinemaContract {
    interface IView extends IBaseView{
        void onCinemaRecommend(CinemaRecommendBean cinemaRecommendBean);
    }
    interface IPresenter{
        void getCinemaRecommend(int page,int count);
    }
    interface IModel{
        void onGetCinemaRecommend(int page,int count,ICinemaRecommendCallBack iCinemaRecommendCallBack);
        interface ICinemaRecommendCallBack{
            void onCinemaRecommend(CinemaRecommendBean cinemaRecommendBean);
        }
    }
}
