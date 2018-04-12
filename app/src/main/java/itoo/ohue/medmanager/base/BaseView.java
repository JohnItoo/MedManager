package itoo.ohue.medmanager.base;

import android.support.annotation.StringRes;

/**
 * Created by OHUE JOHN on 3/31/2018.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter( T presenter);

    void makeToast( String message);

    void makeToast ( @StringRes int messageRes);

    void showEditTextError( String message);

    void showEditTextError(@StringRes int message);

    void setToolbarTitle(@StringRes int message);

}
