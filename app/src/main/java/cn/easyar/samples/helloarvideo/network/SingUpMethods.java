package cn.easyar.samples.helloarvideo.network;


import java.util.concurrent.TimeUnit;

import cn.easyar.samples.helloarvideo.entiy.SingupResult;
import cn.easyar.samples.helloarvideo.network.api.SingUpServer;
import cn.easyar.samples.helloarvideo.network.url.Url;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by BlackBeard丶 on 2017/03/05.
 */
public class SingUpMethods {
    private Retrofit retrofit;
    private SingUpServer singUpServer;
    private static final int DEFAULT_TIMEOUT = 5;

    private SingUpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);


        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Url.LOGIN_URL)
                .build();

        singUpServer = retrofit.create(SingUpServer.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final SingUpMethods INSTANCE = new SingUpMethods() {
        };
    }

    //获取单例
    public static SingUpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void goToSingup(Subscriber<SingupResult> subscriber, String code,  String phone, String password,String authCodeId) {
        singUpServer.register( code, phone, password,authCodeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void sendCode(Subscriber<SingupResult> subscriber,String phone){
        singUpServer.code(phone).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }
}
