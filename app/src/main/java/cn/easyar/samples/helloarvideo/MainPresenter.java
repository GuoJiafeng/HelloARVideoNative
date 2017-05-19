package cn.easyar.samples.helloarvideo;

import cn.easyar.samples.helloarvideo.homepage.HomeFragment;
import cn.easyar.samples.helloarvideo.minepage.MineFrgament;

/**
 * Created by BlackBeard丶 on 2017/5/3.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View addView;

    HomeFragment homeFragment;
    MineFrgament mineFrgament;

    public MainPresenter(MainContract.View addView) {
        this.addView = addView;
    }

    @Override
    public void start() {

    }

    @Override
    public void setDefaultFragment() {
        homeFragment = new HomeFragment();
        addView.showFragmen(homeFragment,"默认显示HmoeFragment");
    }

    @Override
    public void showHomeFragment() {
        homeFragment = new HomeFragment();
        addView.showFragmen(homeFragment,"显示HmoeFragment");

    }

    @Override
    public void showMineFragment() {
        mineFrgament = new MineFrgament();
        addView.showFragmen(mineFrgament,"默认显示HmoeFragment");
    }
}
