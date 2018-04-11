package itoo.ohue.medmanager.main;


import android.support.design.widget.NavigationView;

import itoo.ohue.medmanager.base.BasePresenter;
import itoo.ohue.medmanager.base.BaseView;

/**
 * Created by OHUE JOHN on 3/31/2018.
 */

public interface MainContract {

    // interface view for callbacks
     interface View extends BaseView<MainContract.Presenter>{
           void closeDrawer();

           void inflateFragment(android.support.v4.app.Fragment ft);

        void setNavListener(NavigationView.OnNavigationItemSelectedListener navListener);


     }

     // interface presenter for user actions
    interface Presenter extends BasePresenter {


    }
}
