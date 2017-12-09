package cn.easyar.samples.helloarvideo.network.api;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by BlackBeard丶 on 2017/5/20.
 */

public interface DownloadServer {
    @POST("top250")
    Observable<ResponseBody> getImageList(@Field("session_id") String  Session_Id);

    @POST("top250")
    Observable<ResponseBody> getImageJson(@Field("session_id") String  Session_Id);
}
