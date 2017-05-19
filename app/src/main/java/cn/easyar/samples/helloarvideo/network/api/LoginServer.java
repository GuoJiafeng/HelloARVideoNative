package cn.easyar.samples.helloarvideo.network.api;


import cn.easyar.samples.helloarvideo.entiy.LoginResult;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by BlackBeard丶 on 2017/03/02.
 */
public interface LoginServer {

    @FormUrlEncoded
    @POST("loginAction")
    Observable<LoginResult> login(@Field("phone") String phone, @Field("password") String password,@Field("device_type") String device_type,@Field("device_code") String device_code);

    @FormUrlEncoded
    @POST("loginAction")
    Observable<LoginResult> checklogin(@Field("SessionID") String SessionID);
}
