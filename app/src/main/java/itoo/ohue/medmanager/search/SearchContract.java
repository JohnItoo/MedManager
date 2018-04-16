package itoo.ohue.medmanager.search;

import android.database.Cursor;

import itoo.ohue.medmanager.base.BasePresenter;
import itoo.ohue.medmanager.base.BaseView;

public interface SearchContract {
    interface VIew extends BaseView<SearchContract.Presenter> {
        void showSearchResults(Cursor searchCursor);
    }

    public interface  Presenter extends BasePresenter {
        void search(String nameQuery);
    }
}
