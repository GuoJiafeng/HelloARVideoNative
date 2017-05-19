package cn.easyar.samples.helloarvideo.network.api;


import cn.easyar.samples.helloarvideo.entiy.SingupResult;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by BlackBeard丶 on 2017/03/02.
 */
public interface SingUpServer {
    @FormUrlEncoded
    @POST("userAction")
    Observable<SingupResult> register(@Field("code") String code,@Field("username") String name, @Field("password") String password,@Field("authCodeId") String authCodeId);


    @FormUrlEncoded
    @POST("userAction")
    Observable<SingupResult> code(@Field("username") String phone);
}


