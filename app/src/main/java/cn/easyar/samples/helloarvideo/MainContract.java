package cn.easyar.samples.helloarvideo;

import android.app.Fragment;

/**
 * Created by BlackBeard丶 on 2017/5/3.
 */

public interface MainContract {
    interface  View extends BaseView<Presenter>{

        void showFragmen(Fragment fragment,String msg);

    }
    interface Presenter extends BasePresenter{
        void setDefaultFragment();
        void showHomeFragment();
        void showMineFragment();
    }
}
