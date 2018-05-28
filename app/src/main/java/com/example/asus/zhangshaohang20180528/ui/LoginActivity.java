package com.example.asus.zhangshaohang20180528.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.zhangshaohang20180528.R;
import com.example.asus.zhangshaohang20180528.base.BaseActivity;
import com.example.asus.zhangshaohang20180528.bean.UserBean;
import com.example.asus.zhangshaohang20180528.component.DaggerHttpComponent;
import com.example.asus.zhangshaohang20180528.media.MediaPlayerActivity;
import com.example.asus.zhangshaohang20180528.modle.HttpModule;
import com.example.asus.zhangshaohang20180528.ui.contract.LoginContract;
import com.example.asus.zhangshaohang20180528.ui.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {

    private EditText mEdMobile;
    private EditText mEdPassword;
    /**
     * 登录
     */
    private Button mBtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }



    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }


    @Override
    public void loginSuccess(UserBean userBean) {
        Toast.makeText(LoginActivity.this,userBean.getMsg(),Toast.LENGTH_LONG).show();

        Intent intent=new Intent(this, MediaPlayerActivity.class);
        startActivity(intent);
    }

    private void initView() {
        mEdMobile = (EditText) findViewById(R.id.ed_mobile);
        mEdPassword = (EditText) findViewById(R.id.ed_password);
        mBtButton = (Button) findViewById(R.id.bt_button);
        mBtButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_button:
                String mobile = mEdMobile.getText().toString();
                String password = mEdPassword.getText().toString();
                mPresenter.login(mobile,password);
                break;
        }
    }
}
