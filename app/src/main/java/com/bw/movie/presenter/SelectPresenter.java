package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.AliPayBean;
import com.bw.movie.bean.BuyTicketBean;
import com.bw.movie.bean.FindCinemasInfoByRegion;
import com.bw.movie.bean.FindMovieScheduleBean;
import com.bw.movie.bean.FindSeatInfoBean;
import com.bw.movie.contract.ISelectContract;
import com.bw.movie.model.SelectModel;

/**
 * Time: 2020/5/9
 * Author: 王冠华
 * Description:
 */
public class SelectPresenter extends BasePresenter implements ISelectContract.IPresenter {

    private SelectModel model;

    public SelectPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new SelectModel();
    }

    @Override
    public void getCinemaByRegion(int movieId, int regionId, int page, int count) {
        model.onGetCinemaByRegion(movieId, regionId, page, count, new ISelectContract.IModel.ICinemaByRegionCallBack() {
            @Override
            public void onCinemaByRegion(FindCinemasInfoByRegion findCinemasInfoByRegion) {
                IBaseView view = getView();
                if(view instanceof ISelectContract.IView){
                    ISelectContract.IView iView= (ISelectContract.IView) view;
                    iView.onCinemaByRegion(findCinemasInfoByRegion);
                }
            }
        });
    }

    @Override
    public void getShowSeat(int hallId) {
        model.onGetShowSeat(hallId, new ISelectContract.IModel.IShowSeatCallBack() {
            @Override
            public void onShowSeat(FindSeatInfoBean findSeatInfoBean) {
                IBaseView view = getView();
                if(view instanceof ISelectContract.IView){
                    ISelectContract.IView iView= (ISelectContract.IView) view;
                    iView.onShowSeat(findSeatInfoBean);
                }
            }
        });
    }

    @Override
    public void getFindMovieSchedule(int movieId, int cinemaId) {
        model.onGetFindMovieSchedule(movieId, cinemaId, new ISelectContract.IModel.IFindMovieScheduleCallBack() {
            @Override
            public void onFindMovieSchedule(FindMovieScheduleBean findMovieScheduleBean) {
                IBaseView view = getView();
                if(view instanceof ISelectContract.IView){
                    ISelectContract.IView iView= (ISelectContract.IView) view;
                    iView.onFindMovieSchedule(findMovieScheduleBean);
                }
            }
        });
    }

    @Override
    public void getBuyTicket(int scheduleId, String seat, String sign) {
        model.onGetBuyTicket(scheduleId, seat, sign, new ISelectContract.IModel.IBuyTicketCallBack() {
            @Override
            public void onBuyTicket(BuyTicketBean buyTicketBean) {
                IBaseView view = getView();
                if(view instanceof ISelectContract.IView){
                    ISelectContract.IView iView= (ISelectContract.IView) view;
                    iView.onBuyTicket(buyTicketBean);
                }
            }
        });
    }

    @Override
    public void getPay(int payType, String orderId) {
        model.onGetPay(payType, orderId, new ISelectContract.IModel.IPayCallBack() {
            @Override
            public void onPay(AliPayBean aliPayBean) {
                IBaseView view = getView();
                if(view instanceof ISelectContract.IView){
                    ISelectContract.IView iView= (ISelectContract.IView) view;
                    iView.onPay(aliPayBean);
                }
            }
        });
    }
}
