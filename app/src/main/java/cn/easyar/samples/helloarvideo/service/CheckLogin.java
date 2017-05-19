package cn.easyar.samples.helloarvideo.service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import cn.easyar.samples.helloarvideo.entiy.CheckLoginApplication;
import cn.easyar.samples.helloarvideo.entiy.LoginResult;
import cn.easyar.samples.helloarvideo.network.LoginMethods;
import rx.Subscriber;

/**
 * Created by BlackBeard丶 on 2017/03/08.
 */
public class CheckLogin extends Service {
    private Subscriber subscriber;
    private CheckLoginApplication checkLoginApplication;
    private boolean issuccrssful;

    public CheckLogin() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("11111111111");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Toast.makeText(this,"创建",Toast.LENGTH_LONG).show();
        Log.d("My", "start");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        checkLogin();
        //Toast.makeText(this,"启动",Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //  Toast.makeText(this,"销毁",Toast.LENGTH_LONG).show();
        super.onDestroy();

    }

    private void checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo", Activity.MODE_APPEND);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String SessionID = sharedPreferences.getString("SessionID", "");
        //String password = sharedPreferences.getString("password","");
       // Toast.makeText(this, "电话是：" + SessionID.toString().trim(), Toast.LENGTH_LONG).show();
        if (!SessionID.equals("")) {
            //Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_LONG).show();

            subscriber = new Subscriber<LoginResult>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    //  Toast.makeText(getApplicationContext(),e.getMessage().toString()+"网络错误！",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNext(LoginResult loginResult) {
                    String code = loginResult.getCode();

                    if (!code.equals("200")) {
                        checkLoginApplication = (CheckLoginApplication) getApplication();
                        checkLoginApplication.setIsLogin(true);
                       // Toast.makeText(getApplicationContext(), "登陆成功!", Toast.LENGTH_LONG).show();

                    } else {
                        checkLoginApplication = (CheckLoginApplication) getApplication();
                        checkLoginApplication.setIsLogin(false);
                        editor.clear().commit();

                    }
                }
            };

            LoginMethods.getInstance().checkLogin(subscriber, SessionID);
        } else {
            checkLoginApplication = (CheckLoginApplication) getApplication();
            checkLoginApplication.setIsLogin(false);
            editor.clear().commit();
            // Toast.makeText(getApplicationContext(),"清除成功",Toast.LENGTH_LONG).show();


        }


    }

}
