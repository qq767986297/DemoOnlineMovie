package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.CinemaRecommendBean;
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
}
