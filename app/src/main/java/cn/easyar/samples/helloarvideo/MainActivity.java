package cn.easyar.samples.helloarvideo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;




/**
 * Created by BlackBeard丶 on 2017/04/13.
 */

public class MainActivity extends Activity implements MainContract.View ,View.OnClickListener {



    RelativeLayout home_Fragment;

    RelativeLayout mine_Fragment;
    RelativeLayout mid_fragment;


    RelativeLayout layout;


    private MainPresenter mainContract;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mian_activity);
        setDefaultFragment();
        setChenjinshitongzhilan();

        home_Fragment = (RelativeLayout)findViewById(R.id.homefragment);
        home_Fragment.setOnClickListener(this);
        mine_Fragment = (RelativeLayout)findViewById(R.id.minefragment);
        mine_Fragment.setOnClickListener(this);
        mid_fragment = (RelativeLayout)findViewById(R.id.midfragment);
        mid_fragment.setOnClickListener(this);

        layout = (RelativeLayout)findViewById(R.id.layout);


    }

    private void startAR() {

        Intent intent = new Intent(this, ARActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            intent.putExtras(extras);
        startActivity(intent);

    }

    private void setDefaultFragment() {
        mainContract = new MainPresenter(this);
        mainContract.setDefaultFragment();
        home_Fragment = (RelativeLayout)findViewById(R.id.homefragment);
        home_Fragment.setBackgroundResource(R.drawable.home_select_4);

    }




    private void setChenjinshitongzhilan() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    public void showFragmen(Fragment fragment, String msg) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mian_content, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minefragment:
                mainContract.showMineFragment();
                mine_Fragment.setBackgroundResource(R.drawable.mine_select_4);
                home_Fragment.setBackgroundResource(R.drawable.home_4);
                break;
            case R.id.homefragment:
                mainContract.showHomeFragment();
                home_Fragment.setBackgroundResource(R.drawable.home_select_4);
                mine_Fragment.setBackgroundResource(R.drawable.mine_4);
                break;
            case R.id.midfragment:
                startAR();
                break;
        }
    }
}
