package com.example.asus.zhangshaohang20180528.ui.presenter;

import com.example.asus.zhangshaohang20180528.base.BasePresenter;
import com.example.asus.zhangshaohang20180528.bean.UserBean;
import com.example.asus.zhangshaohang20180528.net.LoginApi;
import com.example.asus.zhangshaohang20180528.ui.contract.LoginContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginApi loginApi;
    @Inject
    public LoginPresenter(LoginApi loginApi){
        this.loginApi=loginApi;
    }

    @Override
    public void login(String mobile, String password) {

        loginApi.login(mobile, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        mview.loginSuccess(userBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
