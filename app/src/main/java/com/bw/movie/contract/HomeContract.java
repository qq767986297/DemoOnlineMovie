package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.HomeBannerBean;
import com.bw.movie.bean.HomeHotMovieBean;
import com.bw.movie.bean.HomeReleaseMovieBean;
import com.bw.movie.bean.HomeSearchMovieBean;
import com.bw.movie.bean.HomeSoonMovieBean;
import com.bw.movie.bean.MovieReserveBean;

/**
 * Time: 2020/4/22
 * Author: 王冠华
 * Description:
 */
public interface HomeContract {
    interface IHomeView extends IBaseView{
        void onBannerSuccess(HomeBannerBean homeBannerBean);
        void onHomeReleaseMovieSuccess(HomeReleaseMovieBean homeReleaseMovieBean);
        void onHomeSoonMovieSuccess(HomeSoonMovieBean homeSoonMovieBean);
        void onHomeHotMovieSuccess(HomeHotMovieBean homeHotMovieBean);
        void onHomeSerachMovieSuccess(HomeSearchMovieBean homeSearchMovieBean);
        void onMovieReserve(MovieReserveBean movieReserveBean);
    }
    interface IHomePresenter{
        void getBanner();
        void getHomeReleaseMovie(int page,int count);
        void getHomeSoonMovie(int page,int count);
        void getHomeHotMovie(int page,int count);
        void getHomeSerachMovie(String keyword,int page,int count);
        void getMovieReserve(int movieId);
    }
    interface IHomeModel{
        void onGetBanner(IBannerCallBack iBannerCallBack);
        void onGetHomeReleaseMovie(int page,int count,IReleaseMovieCallBack iReleaseMovieCallBack);
        void onGetHomeSoonMovie(int page,int count,ISoonMovieCallBack iSoonMovieCallBack);
        void onGetHomeHotMovie(int page,int count,IHotMovieCallBack iHotMovieCallBack);
        void onGetHomeSerachMovie(String keyword,int page,int count,ISerachMovieCallBack iSerachMovieCallBack);
        void onGetMovieReserve(int movieId,IMovieReseeveICallBack iMovieReseeveICallBack);
        interface IBannerCallBack{
            void onBannerSuccess(HomeBannerBean homeBannerBean);
        }
        interface IReleaseMovieCallBack{
            void onHomeReleaseMovieSuccess(HomeReleaseMovieBean homeReleaseMovieBean);
        }
        interface ISoonMovieCallBack{
            void onHomeSoonMovieSuccess(HomeSoonMovieBean homeSoonMovieBean);
        }
        interface IHotMovieCallBack{
            void onHomeHotMovieSuccess(HomeHotMovieBean homeHotMovieBean);
        }
        interface ISerachMovieCallBack{
            void onHomeSerachMovieSuccess(HomeSearchMovieBean homeSearchMovieBean);
        }
        interface IMovieReseeveICallBack{
            void onMovieReserve(MovieReserveBean movieReserveBean);
        }
    }
}
