package cn.easyar.samples.helloarvideo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.easyar.samples.helloarvideo.network.HttpClientUtils;
import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by BlackBeard丶 on 2017/5/9.
 */

public class DownloadService  extends Service {

private Subscriber subscriber;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        WriteJson();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void DownloadImage(){
          subscriber = new Subscriber<ResponseBody>() {
              @Override
              public void onCompleted() {

              }

              @Override
              public void onError(Throwable e) {

              }

              @Override
              public void onNext(ResponseBody responseBody) {
                  try {
                      JSONArray arr = new JSONArray(responseBody.toString());
                      for (int i = 0; i < arr.length(); i++) {
                          JSONObject temp = (JSONObject) arr.get(i);
                          String img_name = temp.getString("img_name");
                          String img_path = temp.getString("img_path");
                          HttpClientUtils httpClientUtils = new HttpClientUtils(img_path.toString(),"",img_name.toString());
                          httpClientUtils.downloadFile();

                      }

                  }catch (Exception e){
                     e.printStackTrace();
                  }
              }


          };
    }
    private void WriteJson(){

     subscriber = new Subscriber<ResponseBody>(){

         @Override
         public void onCompleted() {

         }

         @Override
         public void onError(Throwable e) {

         }

         @Override
         public void onNext(ResponseBody responseBody) {
             HttpClientUtils httpClientUtils = new HttpClientUtils("","","");
             httpClientUtils.writeSDFile("郭家丰","targets.json");

         }
     };
    }
}
