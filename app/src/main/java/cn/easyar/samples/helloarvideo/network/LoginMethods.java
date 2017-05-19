package cn.easyar.samples.helloarvideo.network;


import java.util.concurrent.TimeUnit;

import cn.easyar.samples.helloarvideo.entiy.LoginResult;
import cn.easyar.samples.helloarvideo.network.api.LoginServer;
import cn.easyar.samples.helloarvideo.network.url.Url;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by BlackBeard丶 on 2017/03/04.
 */
public class LoginMethods {
    private Retrofit retrofit;
    private LoginServer loginServer;

    private static final int DEFAULT_TIMEOUT = 5;

    private LoginMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);


        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Url.LOGIN_URL)
                .build();

        loginServer = retrofit.create(LoginServer.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final LoginMethods INSTANCE = new LoginMethods();
    }

    //获取单例
    public static LoginMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void goToLogin(Subscriber<LoginResult> subscriber, String phone, String password,String device_code,String device_type) {
        loginServer.login(phone, password,device_code,device_type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void checkLogin(Subscriber<LoginResult> subscriber, String SessionID) {
        loginServer.checklogin(SessionID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

}
