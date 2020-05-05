package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.UserFollowMovieBean;
import com.bw.movie.contract.IMineContract;
import com.bw.movie.model.MineModel;

/**
 * Time: 2020/4/28
 * Author: 王冠华
 * Description:
 */
public class MinePresenter extends BasePresenter implements IMineContract.IPresenter {


    private MineModel model;

    public MinePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new MineModel();
    }

    @Override
    public void getUserFollowMovie(int page, int count) {
        model.onGetUserFollowMovie(page, count, new IMineContract.IModel.IUserFollowMovieCallBack() {
            @Override
            public void onUserFollowMovie(UserFollowMovieBean userFollowMovieBean) {
                IBaseView view = getView();
                if(view instanceof IMineContract.IView){
                    IMineContract.IView iView= (IMineContract.IView) view;
                    iView.onUserFollowMovie(userFollowMovieBean);
                }
            }
        });
    }
}
