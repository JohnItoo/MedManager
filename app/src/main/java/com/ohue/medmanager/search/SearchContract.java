package com.ohue.medmanager.search;

import android.database.Cursor;

import com.ohue.medmanager.base.BasePresenter;
import com.ohue.medmanager.base.BaseView;

public interface SearchContract {
    interface VIew extends BaseView<Presenter> {
        void showSearchResults(Cursor searchCursor);
    }

    public interface  Presenter extends BasePresenter {
        void search(String nameQuery);
    }
}
