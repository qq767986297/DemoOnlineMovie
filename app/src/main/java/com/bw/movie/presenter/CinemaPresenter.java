package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
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
import com.bw.movie.contract.ICinemaContract;
import com.bw.movie.model.CinemaModel;

/**
 * Time: 2020/5/7
 * Author: 王冠华
 * Description:
 */
public class CinemaPresenter extends BasePresenter implements ICinemaContract.IPresenter {

    private CinemaModel model;

    public CinemaPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new CinemaModel();
    }

    @Override
    public void getCinemaRecommend(int page, int count) {
        model.onGetCinemaRecommend(page, count, new ICinemaContract.IModel.ICinemaRecommendCallBack() {
            @Override
            public void onCinemaRecommend(CinemaRecommendBean cinemaRecommendBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaRecommend(cinemaRecommendBean);
                }
            }
        });
    }

    @Override
    public void getCinemaNearby(String longitude, String latitude, int page, int count) {
        model.onGetCinemaNearby(longitude, latitude, page, count, new ICinemaContract.IModel.ICinemaNearbyCallBack() {
            @Override
            public void onCinemaNearby(CinemaNearbyBean cinemaNearbyBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaNearby(cinemaNearbyBean);
                }
            }
        });
    }

    @Override
    public void getCinemaRegion() {
        model.onGetCinemaRegion(new ICinemaContract.IModel.ICinemaRegionCallBack() {
            @Override
            public void onCinemaRegion(CinemaRegionBean cinemaRegionBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaRegion(cinemaRegionBean);
                }
            }
        });
    }

    @Override
    public void getCinemaLink(int regionId) {
        model.onGetCinemaLink(regionId, new ICinemaContract.IModel.ICinemaLinkCallBack() {
            @Override
            public void onCinemaLink(CinemaLinkBean cinemaLinkBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaLink(cinemaLinkBean);
                }
            }
        });
    }

    @Override
    public void getCinemaDetail(int cinemaId) {
        model.onGetCinemaDetail(cinemaId, new ICinemaContract.IModel.ICinemaDetailCallBack() {
            @Override
            public void onCinemaDetail(CinemaDetailBean cinemaDetailBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaDetail(cinemaDetailBean);
                }
            }
        });
    }

    @Override
    public void getCinemaDetailComment(int cinemaId, int page, int count) {
        model.onGetCinemaDetailComment(cinemaId, page, count, new ICinemaContract.IModel.ICinemaDtailCommentCallBack() {
            @Override
            public void onCinemaDetailComment(CinemaDetailCommentBean cinemaDetailCommentBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaDetailComment(cinemaDetailCommentBean);
                }
            }
        });
    }

    @Override
    public void getCinemaFollow(int cinemaId) {
        model.onGetCinemaFollow(cinemaId, new ICinemaContract.IModel.ICinemaFollowCallBack() {
            @Override
            public void onCinemaFollow(CinemaFollowBean cinemaFollowBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaFollow(cinemaFollowBean);
                }
            }
        });
    }

    @Override
    public void getCinemaCanelFollow(int cinemaId) {
        model.onGetCinemaCanelFollow(cinemaId, new ICinemaContract.IModel.ICinemaCanelFollowCallBack() {
            @Override
            public void onCinemaCanleFollow(CinemaCanelFollowBean cinemaCanelFollowBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaCanleFollow(cinemaCanelFollowBean);
                }
            }
        });
    }

    @Override
    public void getCinemaCommentGood(int commentId) {
        model.onGetCinemaCommentGood(commentId, new ICinemaContract.IModel.ICinemaCommentGoodCallBack() {
            @Override
            public void onCinemaCommentGood(CinemaCommentGoodBean cinemaCommentGoodBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaCommentGood(cinemaCommentGoodBean);
                }
            }
        });
    }

    @Override
    public void getCinemaSchedu(int cinemaId, int page, int count) {
        model.onGetCinemaSchedu(cinemaId, page, count, new ICinemaContract.IModel.ICinemaScheduCallBack() {
            @Override
            public void onCinemaSchedu(CinemaScheduleListBean cinemaScheduleListBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaSchedu(cinemaScheduleListBean);
                }
            }
        });
    }

    @Override
    public void getCinemaData() {
        model.onGetCinemaData(new ICinemaContract.IModel.ICinemaDataCallBack() {
            @Override
            public void onCinemaData(FindDataBean findDataBean) {
                IBaseView view = getView();
                if(view instanceof ICinemaContract.IView){
                    ICinemaContract.IView iView= (ICinemaContract.IView) view;
                    iView.onCinemaData(findDataBean);
                }
            }
        });
    }
}
