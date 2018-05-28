package com.example.asus.zhangshaohang20180528.ui.contract;

import com.example.asus.zhangshaohang20180528.base.BaseContract;
import com.example.asus.zhangshaohang20180528.bean.UserBean;

public interface LoginContract {
    interface View  extends BaseContract.BaseView{
        //成功
        void loginSuccess(UserBean userBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        //登录
        void login(String mobile,String password);
    }
}
