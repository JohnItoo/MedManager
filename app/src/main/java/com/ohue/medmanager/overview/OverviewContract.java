package com.ohue.medmanager.overview;

import android.database.Cursor;

import com.ohue.medmanager.base.BasePresenter;
import com.ohue.medmanager.base.BaseView;

public interface OverviewContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        public Cursor getAllMeds ();
    }
}
