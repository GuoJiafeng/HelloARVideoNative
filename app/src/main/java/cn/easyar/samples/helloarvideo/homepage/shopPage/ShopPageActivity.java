package cn.easyar.samples.helloarvideo.homepage.shopPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import cn.easyar.samples.helloarvideo.R;

/**
 * Created by BlackBeard丶 on 2017/5/13.
 */

public class ShopPageActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_page_activity);
        setChenjinshitongzhilan();
        imageView = (LinearLayout)findViewById(R.id.image01);
        imageView.setOnClickListener(this);
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
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.image01:
            gotoGoodDetail();
             break;
     }
    }
    private void gotoGoodDetail(){
        Intent intent = new Intent(this,GoodDetialActivity.class);
        startActivity(intent);
    }
}
