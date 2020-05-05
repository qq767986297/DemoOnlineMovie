package com.bw.movie.model;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendEmailCodeBean;
import com.bw.movie.contract.ILoginContract;
import com.bw.movie.utils.RetrofiManger;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/4/20
 * Author: 王冠华
 * Description:
 */
public class LoginModel implements ILoginContract.ILoginModel {
    @Override
    public void onGetEamilCode(String eamil, final IEmailCodeCallBack iEmailCodeCallBack) {
        RetrofiManger.getInstance().getApis().getRegEmailCode(eamil)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendEmailCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendEmailCodeBean sendEmailCodeBean) {
                        if(iEmailCodeCallBack!=null){
                            iEmailCodeCallBack.onSendEmailSuccess(sendEmailCodeBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(iEmailCodeCallBack!=null){
                            iEmailCodeCallBack.onSendEmailFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetRegsiter(String nickName, String pwd, String email, String code, final IRegsterCallBack iRegsterCallBack) {
        RetrofiManger.getInstance().getApis().getRegsiter(nickName, pwd, email, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if(iRegsterCallBack!=null){
                            iRegsterCallBack.onRegSuccess(registerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(iRegsterCallBack!=null){
                            iRegsterCallBack.onRegFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetLogin(String email, String pwd, final ILoginCallBack iLoginCallBack) {
        RetrofiManger.getInstance().getApis().getLogin(email, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if(iLoginCallBack!=null){
                            iLoginCallBack.onLoginSuccess(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(iLoginCallBack!=null){
                            iLoginCallBack.onLoginFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
