package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.AddMovieCommentBean;
import com.bw.movie.contract.IAddContract;
import com.bw.movie.model.AddModel;

/**
 * Time: 2020/5/1
 * Author: 王冠华
 * Description:
 */
public class AddPresenter extends BasePresenter implements IAddContract.IPresenter {

    private AddModel model;

    public AddPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new AddModel();
    }

    @Override
    public void getAddMovieComment(int movieId, String content, double score) {
        model.onGetAddMovieComment(movieId, content, score, new IAddContract.IModel.IAddMovieCommentCallBack() {
            @Override
            public void onAddMovieComment(AddMovieCommentBean addMovieCommentBean) {
                IBaseView view = getView();
                if(view instanceof IAddContract.IView){
                    IAddContract.IView iView= (IAddContract.IView) view;
                    iView.onAddMovieComment(addMovieCommentBean);
                }
            }
        });
    }
}
