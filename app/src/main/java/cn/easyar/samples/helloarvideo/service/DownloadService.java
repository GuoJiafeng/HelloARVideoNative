package cn.easyar.samples.helloarvideo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

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

    }
    private void WriteJson(){

    }
}
