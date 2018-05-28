package com.example.asus.zhangshaohang20180528.base;

public class BasePresenter<T extends BaseContract.BaseView>  implements BaseContract.BasePresenter<T> {

    protected T mview;

    //绑定
    @Override
    public void attchView(T view) {
        this.mview=view;
    }
    //解绑
    @Override
    public void detachView() {
        if (mview!=null){
            mview=null;
        }
    }
}
