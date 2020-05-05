package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendEmailCodeBean;

/**
 * Time: 2020/4/20
 * Author: 王冠华
 * Description:
 */
public interface ILoginContract {
    interface ILoginView extends IBaseView {
        void onSendEmailSuccess(SendEmailCodeBean sendEmailCodeBean);
        void onSendEmailFailure(String str);
        void onRegSuccess(RegisterBean registerBean);
        void onRegFailure(String str);
        void onLoginSuccess(LoginBean loginBean);
        void onLoginFailure(String str);
    }
    interface ILoginPresenter{
        void getEamilCode(String eamil);
        void getRegsiter(String nickName, String pwd, String email, String code);
        void getLogin(String email, String pwd);
    }
    interface ILoginModel{
        void onGetEamilCode(String eamil, IEmailCodeCallBack iEmailCodeCallBack);
        void onGetRegsiter(String nickName, String pwd, String email, String code, IRegsterCallBack iRegsterCallBack);
        void onGetLogin(String email, String pwd, ILoginCallBack iLoginCallBack);
        interface IEmailCodeCallBack{
            void onSendEmailSuccess(SendEmailCodeBean sendEmailCodeBean);
            void onSendEmailFailure(String str);
        }
        interface IRegsterCallBack{
            void onRegSuccess(RegisterBean registerBean);
            void onRegFailure(String str);
        }
        interface ILoginCallBack{
            void onLoginSuccess(LoginBean loginBean);
            void onLoginFailure(String str);
        }
    }
}
