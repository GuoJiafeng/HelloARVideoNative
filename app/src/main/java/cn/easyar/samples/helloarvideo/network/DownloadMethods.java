package cn.easyar.samples.helloarvideo.network;

import java.util.concurrent.TimeUnit;

import cn.easyar.samples.helloarvideo.network.api.DownloadServer;
import cn.easyar.samples.helloarvideo.network.url.Url;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by BlackBeard丶 on 2017/5/20.
 */

public class DownloadMethods {
    private Retrofit retrofit;
    private DownloadServer downloadServer;

    private static final int DEFAULT_TIMEOUT = 5;

    private DownloadMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);


        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Url.LOGIN_URL)
                .build();

        downloadServer = retrofit.create(DownloadServer.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final DownloadMethods INSTANCE = new DownloadMethods();
    }

    //获取单例
    public static DownloadMethods getInstance() {
        return DownloadMethods.SingletonHolder.INSTANCE;
    }

    public void getImageList(Subscriber<ResponseBody> subscriber, String Session_Id) {
        downloadServer.getImageList(Session_Id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getImageJson(Subscriber<ResponseBody> subscriber, String Session_Id) {
        downloadServer.getImageJson(Session_Id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
