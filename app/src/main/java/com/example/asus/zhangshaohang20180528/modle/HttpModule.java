package com.example.asus.zhangshaohang20180528.modle;

import com.example.asus.zhangshaohang20180528.net.Api;
import com.example.asus.zhangshaohang20180528.net.LoginApi;
import com.example.asus.zhangshaohang20180528.net.LoginApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {

    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder(){
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS);
    }

    @Provides
    LoginApi provideLoginApi(OkHttpClient.Builder builder){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.LoginApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        LoginApiService service = retrofit.create(LoginApiService.class);
        return LoginApi.getLoginApi(service);
    }
}
