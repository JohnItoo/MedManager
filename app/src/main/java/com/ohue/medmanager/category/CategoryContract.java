package com.ohue.medmanager.category;

import android.database.Cursor;

import com.ohue.medmanager.base.BasePresenter;
import com.ohue.medmanager.base.BaseView;

public interface CategoryContract {
    interface View extends BaseView<Presenter> {

    }

    public interface Presenter extends BasePresenter {
        public Cursor getMedsForMonth(int month);
    }
}
