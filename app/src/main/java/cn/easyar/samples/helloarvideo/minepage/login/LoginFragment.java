package cn.easyar.samples.helloarvideo.minepage.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.easyar.samples.helloarvideo.MainActivity;
import cn.easyar.samples.helloarvideo.R;
import cn.easyar.samples.helloarvideo.entiy.CheckLoginApplication;
import cn.easyar.samples.helloarvideo.entiy.LoginResult;
import cn.easyar.samples.helloarvideo.network.LoginMethods;
import rx.Subscriber;

/**
 * Created by BlackBeard丶 on 2017/5/6.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView gotosinup;
    private SingUpFragment singUpFragment;
    private ImageView singup_exit;
    private EditText login_usename;
    private EditText login_password;
    private ImageView login_button;
    private String IMEI;
    private Subscriber subscriber;
    private CheckLoginApplication checkLoginApplication;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_activity_login, container, false);
        gotosinup = (ImageView) view.findViewById(R.id.gotosinup);
        gotosinup.setOnClickListener(this);
        initView();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gotosinup:
                Log.d("ggggg", "kaishi ");
                goToSingUp();
                break;
            case R.id.login_button:
                Login(login_usename.getText().toString(), login_password.getText().toString());
                break;
        }
    }

    private void goToSingUp() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        singUpFragment = new SingUpFragment();
        transaction.replace(R.id.login_activity, singUpFragment);
        transaction.commit();
    }

    private void initView() {
        singup_exit = (ImageView) view.findViewById(R.id.singup_exit);
        singup_exit.setOnClickListener(this);
        login_usename = (EditText) view.findViewById(R.id.login_usename);
        login_password = (EditText) view.findViewById(R.id.login_password);
        login_button = (ImageView) view.findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(getActivity().TELEPHONY_SERVICE);
        IMEI = telephonyManager.getDeviceId();
    }

    private void Login(String username, String password) {
        subscriber = new Subscriber<LoginResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginResult loginResult) {
                if (loginResult.getCode().equals("200")) {
                saveUserInfo(loginResult.getResult().getSessionId().toString(),login_usename.getText().toString());

                }
                if (loginResult.getCode().equals("610")) {
                    Toast.makeText(getContext(), "用户名或密码错误", Toast.LENGTH_LONG).show();
                }
                if (loginResult.getCode().equals("611")) {
                    Toast.makeText(getContext(), "用户已被封号", Toast.LENGTH_LONG).show();
                }
                if (loginResult.getCode().equals("612")) {
                    Toast.makeText(getContext(), "用户已被注销", Toast.LENGTH_LONG).show();
                }
                if (loginResult.getCode().equals("613"))
                    Toast.makeText(getContext(), "保存session信息失败", Toast.LENGTH_LONG).show();


            }
        };
        String type = "android";
        LoginMethods.getInstance().goToLogin(subscriber, username, password, IMEI,type);
    }


    private void saveUserInfo(String SessionID, String phone) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userinfo", getContext().MODE_APPEND);
        SharedPreferences.Editor editUserInfo = sharedPreferences.edit();
        editUserInfo.putString("phone", phone);
        //editUserInfo.putString("password", password);
        editUserInfo.putString("SessionID", SessionID);
        editUserInfo.commit();
        Toast.makeText(getContext(), "登录成功！", Toast.LENGTH_LONG).show();

        checkLoginApplication = (CheckLoginApplication) getActivity().getApplication();
        checkLoginApplication.setIsLogin(true);
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

}
