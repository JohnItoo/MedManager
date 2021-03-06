package com.ohue.medmanager.signup;

import com.ohue.medmanager.base.BasePresenter;
import com.ohue.medmanager.base.BaseView;

/**
 * Created by OHUE JOHN on 4/2/2018.
 */

public interface SignUpContract {

    //attributes for callbacks
    interface View extends BaseView<Presenter> {

    }
    // attributes for userInteraction on SignUp Activity
    interface Presenter extends BasePresenter {
        void proceed(String email, String password);

    }
}
