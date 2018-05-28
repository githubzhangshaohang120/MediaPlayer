package com.example.asus.zhangshaohang20180528.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asus.zhangshaohang20180528.R;
import com.example.asus.zhangshaohang20180528.inter.IBase;

import javax.inject.Inject;

public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements IBase,BaseContract.BaseView {

    @Inject
    protected T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        inject();
        //绑定
        mPresenter.attchView(this);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        mPresenter.detachView();
    }

    public void showLoading(){

    }
    public void dismissLoading(){

    }
}
