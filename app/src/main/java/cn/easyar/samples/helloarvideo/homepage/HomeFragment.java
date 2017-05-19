package cn.easyar.samples.helloarvideo.homepage;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.easyar.samples.helloarvideo.R;
import cn.easyar.samples.helloarvideo.homepage.shopPage.ShopPageActivity;

/**
 * Created by BlackBeard丶 on 2017/04/13.
 */

public class HomeFragment extends Fragment  implements View.OnClickListener {
    private View view;
    ImageView shop_page;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.home_fragment,container,false);
        shop_page =(ImageView)view.findViewById(R.id.shop_page);
        shop_page.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_page:
                gotoShopPage();
                break;

        }
    }
    private void gotoShopPage(){
        Intent intent = new Intent(getContext(), ShopPageActivity.class);
        startActivity(intent);
    }
}
