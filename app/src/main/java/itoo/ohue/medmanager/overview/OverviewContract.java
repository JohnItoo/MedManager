package itoo.ohue.medmanager.overview;

import android.database.Cursor;

import itoo.ohue.medmanager.base.BasePresenter;
import itoo.ohue.medmanager.base.BaseView;

public interface OverviewContract {

    interface View extends BaseView<OverviewContract.Presenter> {

    }

    interface Presenter extends BasePresenter {
        public Cursor getAllMeds ();
    }
}
