package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendEmailCodeBean;
import com.bw.movie.contract.ILoginContract;
import com.bw.movie.presenter.LoginPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.RetrofiManger;
import com.bw.movie.utils.SPUtils;


import org.greenrobot.eventbus.EventBus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.util.regex.Pattern.*;

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView {

    @BindView(R.id.bt_login_forget)
    Button btLoginForget;
    @BindView(R.id.tv_login_reg)
    TextView tvLoginReg;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.et_login_email)
    EditText etLoginEmail;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        etLoginEmail.setText("337371640@qq.com");
        etLoginPwd.setText("qq123123");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public static boolean matachPhone(String str){
        Pattern compile = compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }
    @OnClick({R.id.bt_login_forget, R.id.tv_login_reg, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login_forget:
                Intent intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_reg:
                //跳转00
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_login:
                boolean netWork = RetrofiManger.getInstance().isNetWork(this);
                if(netWork){
                    String email = etLoginEmail.getText().toString();
                    String mpwd = etLoginPwd.getText().toString();
                    String pwd = EncryptUtil.encrypt(mpwd);
                    if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(mpwd)&&matachPhone(email)){
                        BasePresenter presenter = getPresenter();
                        if(presenter instanceof ILoginContract.ILoginPresenter){
                            ((ILoginContract.ILoginPresenter)presenter).getLogin(email,pwd);
                        }
                    }else {
                        Toast.makeText(this, "邮箱或密码输入有误", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(this, "无网络", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        String message = loginBean.getMessage();
        LoginBean.ResultBean result = loginBean.getResult();
        LoginBean.ResultBean.UserInfoBean userInfo = result.getUserInfo();
        String headPic = userInfo.getHeadPic();
        String nickName = userInfo.getNickName();
        int sex = userInfo.getSex();
        String email = userInfo.getEmail();
        EventBus.getDefault().postSticky(loginBean);
        SPUtils.putString(this,"userInfo","NickName",nickName);
        SPUtils.putString(this,"userInfo","headPic",headPic);
        if(message.equals("登陆成功")){
            int userId = result.getUserId();
            String sessionId = result.getSessionId();
            SPUtils.putString(this,"login","userId",userId+"");
            SPUtils.putString(this,"login","sessionId",sessionId+"");
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "您输入的邮箱或密码有误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginFailure(String str) {

    }

    @Override
    public void onSendEmailSuccess(SendEmailCodeBean sendEmailCodeBean) {

    }

    @Override
    public void onSendEmailFailure(String str) {

    }

    @Override
    public void onRegSuccess(RegisterBean registerBean) {

    }

    @Override
    public void onRegFailure(String str) {

    }


}
