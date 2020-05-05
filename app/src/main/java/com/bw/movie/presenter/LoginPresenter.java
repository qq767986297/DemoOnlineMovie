package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendEmailCodeBean;
import com.bw.movie.contract.ILoginContract;
import com.bw.movie.model.LoginModel;

/**
 * Time: 2020/4/20
 * Author: 王冠华
 * Description:
 */
public class LoginPresenter extends BasePresenter implements ILoginContract.ILoginPresenter {

    private LoginModel model;

    public LoginPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new LoginModel();
    }

    @Override
    public void getEamilCode(String eamil) {
        model.onGetEamilCode(eamil, new ILoginContract.ILoginModel.IEmailCodeCallBack() {
            @Override
            public void onSendEmailSuccess(SendEmailCodeBean sendEmailCodeBean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onSendEmailSuccess(sendEmailCodeBean);
                }
            }

            @Override
            public void onSendEmailFailure(String str) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onSendEmailFailure(str);
                }
            }
        });
    }

    @Override
    public void getRegsiter(String nickName, String pwd, String email, String code) {
        model.onGetRegsiter(nickName, pwd, email, code, new ILoginContract.ILoginModel.IRegsterCallBack() {
            @Override
            public void onRegSuccess(RegisterBean registerBean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onRegSuccess(registerBean);
                }
            }

            @Override
            public void onRegFailure(String str) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onRegFailure(str);
                }
            }
        });
    }

    @Override
    public void getLogin(String email, String pwd) {
        model.onGetLogin(email, pwd, new ILoginContract.ILoginModel.ILoginCallBack() {
            @Override
            public void onLoginSuccess(LoginBean loginBean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onLoginSuccess(loginBean);
                }
            }

            @Override
            public void onLoginFailure(String str) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onLoginFailure(str);
                }
            }
        });
    }
}
