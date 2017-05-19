package cn.easyar.samples.helloarvideo.minepage.login;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.easyar.samples.helloarvideo.R;
import cn.easyar.samples.helloarvideo.entiy.AuthCodeId;
import cn.easyar.samples.helloarvideo.entiy.SingupResult;
import cn.easyar.samples.helloarvideo.network.SingUpMethods;
import rx.Subscriber;

/**
 * Created by BlackBeard丶 on 2017/5/6.
 */

public class SingUpFragment extends Fragment implements View.OnClickListener {
    private View view;
    RelativeLayout alredayhavaaccount;
    private LoginFragment loginFragment;
    private ImageView login_exit;
    private EditText singup_phonenumbertext;
    private EditText singup_testnumbertext;
    private TextView singup_gettestnumber;
    private EditText singup_setpassword;
    private RelativeLayout singup_alredayhavaaccount;
    private RelativeLayout singup_singupnow;
    private Subscriber subscriber;
    private String authCodeIdtext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_activity_singup, container, false);
        initView();//初始化资源
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singup_alredayhavaaccount:
                Login();
                break;
            case R.id.singup_singupnow:
                SingupNow(singup_phonenumbertext.getText().toString(), singup_setpassword.getText().toString(), singup_testnumbertext.getText().toString());
                break;
            case R.id.singuo_gettestnumber:
                getTestNumber(singup_phonenumbertext.getText().toString());
                break;

        }

    }

    private void initView() {
        alredayhavaaccount = (RelativeLayout) view.findViewById(R.id.singup_alredayhavaaccount);
        alredayhavaaccount.setOnClickListener(this);
        login_exit = (ImageView) view.findViewById(R.id.login_exit);
        singup_phonenumbertext = (EditText) view.findViewById(R.id.singup_phonenumbertext);
        singup_testnumbertext = (EditText) view.findViewById(R.id.singup_testnumbertext);
        singup_gettestnumber = (TextView) view.findViewById(R.id.singuo_gettestnumber);
        singup_setpassword = (EditText) view.findViewById(R.id.singup_setpassword);
        singup_singupnow = (RelativeLayout) view.findViewById(R.id.singup_singupnow);
        singup_alredayhavaaccount = (RelativeLayout) view.findViewById(R.id.singup_alredayhavaaccount);
    }

    private void SingupNow(String phone, String password, String testnumber) {
        subscriber = new Subscriber<SingupResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onNext(SingupResult singupResult) {
                 if (singupResult.getCode().equals("200")){
                     Toast.makeText(getContext(),"注册成功",Toast.LENGTH_LONG).show();
                     Login();
                 }
                if (singupResult.getCode().equals("601")){
                    Toast.makeText(getContext(),"验证码错误",Toast.LENGTH_LONG).show();
                }
                if (singupResult.getCode().equals("602")){
                    Toast.makeText(getContext(),"用户名已注册",Toast.LENGTH_LONG).show();
                }
                if (singupResult.getCode().equals("603")){
                    Toast.makeText(getContext(),"验证码已过期",Toast.LENGTH_LONG).show();
                }
                if (singupResult.getCode().equals("604")){
                    Toast.makeText(getContext(),"验证码不存在",Toast.LENGTH_LONG).show();
                }
                if (singupResult.getCode().equals("605")){
                    Toast.makeText(getContext(),"注册失败",Toast.LENGTH_LONG).show();
                }


            }

        };

        SingUpMethods.getInstance().goToSingup(subscriber, testnumber, phone, password,authCodeIdtext);
    }


    private void Login() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        loginFragment = new LoginFragment();
        //singUpFragment = new SingUpFragment();
        transaction.replace(R.id.login_activity, loginFragment);
        transaction.commit();
    }

    private void getTestNumber(String phone) {
subscriber = new Subscriber<AuthCodeId>() {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(AuthCodeId authCodeId) {
        authCodeIdtext = authCodeId.getResult().getAuthCodeId();
    }
};

SingUpMethods.getInstance().sendCode(subscriber,phone);
    }

}
