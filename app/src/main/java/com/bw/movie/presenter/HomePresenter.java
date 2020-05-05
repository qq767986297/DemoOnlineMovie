package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.HomeBannerBean;
import com.bw.movie.bean.HomeHotMovieBean;
import com.bw.movie.bean.HomeReleaseMovieBean;
import com.bw.movie.bean.HomeSearchMovieBean;
import com.bw.movie.bean.HomeSoonMovieBean;
import com.bw.movie.bean.MovieReserveBean;
import com.bw.movie.contract.HomeContract;
import com.bw.movie.model.HomeModel;

/**
 * Time: 2020/4/22
 * Author: 王冠华
 * Description:
 */
public class HomePresenter extends BasePresenter implements HomeContract.IHomePresenter {

    private HomeModel model;

    public HomePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new HomeModel();
    }

    @Override
    public void getBanner() {
        model.onGetBanner(new HomeContract.IHomeModel.IBannerCallBack() {
            @Override
            public void onBannerSuccess(HomeBannerBean homeBannerBean) {
                IBaseView view = getView();
                if(view instanceof HomeContract.IHomeView){
                    HomeContract.IHomeView iView= (HomeContract.IHomeView) view;
                    iView.onBannerSuccess(homeBannerBean);
                }
            }
        });
    }

    @Override
    public void getHomeReleaseMovie(int page, int count) {
        model.onGetHomeReleaseMovie(page, count, new HomeContract.IHomeModel.IReleaseMovieCallBack() {
            @Override
            public void onHomeReleaseMovieSuccess(HomeReleaseMovieBean homeReleaseMovieBean) {
                IBaseView view = getView();
                if(view instanceof HomeContract.IHomeView){
                    HomeContract.IHomeView iView= (HomeContract.IHomeView) view;
                    iView.onHomeReleaseMovieSuccess(homeReleaseMovieBean);
                }
            }
        });
    }

    @Override
    public void getHomeSoonMovie(int page, int count) {
        model.onGetHomeSoonMovie(page, count, new HomeContract.IHomeModel.ISoonMovieCallBack() {
            @Override
            public void onHomeSoonMovieSuccess(HomeSoonMovieBean homeSoonMovieBean) {
                IBaseView view = getView();
                if(view instanceof HomeContract.IHomeView){
                    HomeContract.IHomeView iView= (HomeContract.IHomeView) view;
                    iView.onHomeSoonMovieSuccess(homeSoonMovieBean);
                }
            }
        });
    }

    @Override
    public void getHomeHotMovie(int page, int count) {
        model.onGetHomeHotMovie(page, count, new HomeContract.IHomeModel.IHotMovieCallBack() {
            @Override
            public void onHomeHotMovieSuccess(HomeHotMovieBean homeHotMovieBean) {
                IBaseView view = getView();
                if(view instanceof HomeContract.IHomeView){
                    HomeContract.IHomeView iView= (HomeContract.IHomeView) view;
                    iView.onHomeHotMovieSuccess(homeHotMovieBean);
                }
            }
        });
    }

    @Override
    public void getHomeSerachMovie(String keyword, int page, int count) {
        model.onGetHomeSerachMovie(keyword, page, count, new HomeContract.IHomeModel.ISerachMovieCallBack() {
            @Override
            public void onHomeSerachMovieSuccess(HomeSearchMovieBean homeSearchMovieBean) {
                IBaseView view = getView();
                if(view instanceof HomeContract.IHomeView){
                    HomeContract.IHomeView iView= (HomeContract.IHomeView) view;
                    iView.onHomeSerachMovieSuccess(homeSearchMovieBean);
                }
            }
        });
    }

    @Override
    public void getMovieReserve(int movieId) {
        model.onGetMovieReserve(movieId, new HomeContract.IHomeModel.IMovieReseeveICallBack() {
            @Override
            public void onMovieReserve(MovieReserveBean movieReserveBean) {
                IBaseView view = getView();
                if(view instanceof HomeContract.IHomeView){
                    HomeContract.IHomeView iView= (HomeContract.IHomeView) view;
                    iView.onMovieReserve(movieReserveBean);
                }
            }
        });
    }
}
