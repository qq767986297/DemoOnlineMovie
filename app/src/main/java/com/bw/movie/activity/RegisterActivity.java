package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements ILoginContract.ILoginView {


    @BindView(R.id.tv_reg_name)
    EditText tvRegName;
    @BindView(R.id.tv_reg_email)
    EditText tvRegEmail;
    @BindView(R.id.tv_reg_pwd)
    EditText tvRegPwd;
    @BindView(R.id.tv_reg_code)
    EditText tvRegCode;
    @BindView(R.id.bt_reg_getcode)
    Button btRegGetcode;
    @BindView(R.id.tv_reg_tologin)
    TextView tvRegTologin;
    @BindView(R.id.bt_reg)
    Button btReg;
    private String message;

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


    }


    @OnClick({R.id.bt_reg_getcode, R.id.tv_reg_tologin, R.id.bt_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_reg_getcode:
                String emails = tvRegEmail.getText().toString();
                BasePresenter presenters = getPresenter();
                if(presenters instanceof ILoginContract.ILoginPresenter){
                    ((ILoginContract.ILoginPresenter)presenters).getEamilCode(emails);
                }
                
                break;
            case R.id.tv_reg_tologin:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_reg:
                boolean netWork = RetrofiManger.getInstance().isNetWork(this);
                if(netWork){
                    String mpwd = tvRegPwd.getText().toString();
                    String pwd = EncryptUtil.encrypt(mpwd);
                    Log.i("xxx",pwd+"");
                    String name = tvRegName.getText().toString();
                    String code = tvRegCode.getText().toString();
                    String email = tvRegEmail.getText().toString();
                    if(!TextUtils.isEmpty(mpwd)&&!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(code)&&!TextUtils.isEmpty(email)){
                        BasePresenter presenter = getPresenter();
                        if(presenter instanceof ILoginContract.ILoginPresenter){
                            ((ILoginContract.ILoginPresenter)presenter).getEamilCode(email);
                            ((ILoginContract.ILoginPresenter)presenter).getRegsiter(name,pwd,email,code);
                        }
                    }else {
                        Toast.makeText(this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
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
    public void onSendEmailSuccess(SendEmailCodeBean sendEmailCodeBean) {
        message = sendEmailCodeBean.getMessage();
        Toast.makeText(this, ""+ message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendEmailFailure(String str) {

    }

    @Override
    public void onRegSuccess(RegisterBean registerBean) {
        String message = registerBean.getMessage();
        if(message.equals("注册成功")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegFailure(String str) {

    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {

    }

    @Override
    public void onLoginFailure(String str) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
