package cn.easyar.samples.helloarvideo.minepage;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.easyar.samples.helloarvideo.R;
import cn.easyar.samples.helloarvideo.entiy.CheckLoginApplication;
import cn.easyar.samples.helloarvideo.minepage.login.LoginActivity;
import cn.easyar.samples.helloarvideo.minepage.myinfo.MyInfoActivity;
import cn.easyar.samples.helloarvideo.minepage.myorder.MyOrderActivity;

/**
 * Created by BlackBeard丶 on 2017/04/13.
 */

public class MineFrgament extends Fragment implements View.OnClickListener {
    private View view;
    LinearLayout minefragment_myinfo;
    ImageView gotomyoder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fragment, container, false);
        minefragment_myinfo = (LinearLayout)view.findViewById(R.id.minefragment_myinfo);
        minefragment_myinfo.setOnClickListener(this);
        gotomyoder   = (ImageView)view.findViewById(R.id.gotomyorder);
        gotomyoder.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.minefragment_myinfo:
                judgeLoginStuta();
                break;
            case R.id.gotomyorder:
                gotoMyOrder();
                break;

        }

    }
    private void judgeLoginStuta(){
        CheckLoginApplication checkLoginApplication = (CheckLoginApplication) getActivity().getApplication();
        if (checkLoginApplication.isLogin()) {
            goTomyinfo();
        } else {
            Toast.makeText(getContext(), "请先登录！", Toast.LENGTH_LONG).show();
            goTologin();
        }

    }

    private void goTologin() {
        Intent in = new Intent(getContext(), LoginActivity.class);
        startActivity(in);

    }

    private void gotoMyOrder(){
        Intent intent = new Intent(getContext(), MyOrderActivity.class);
        startActivity(intent);
    }

    private void goTomyinfo() {
        Intent in = new Intent(getContext(), MyInfoActivity.class);
        startActivity(in);
    }
}