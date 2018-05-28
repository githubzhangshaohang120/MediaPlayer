package com.example.asus.zhangshaohang20180528.component;

import com.example.asus.zhangshaohang20180528.modle.HttpModule;
import com.example.asus.zhangshaohang20180528.ui.LoginActivity;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(LoginActivity loginActivity);
}

