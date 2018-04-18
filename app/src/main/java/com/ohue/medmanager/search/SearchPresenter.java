package com.ohue.medmanager.search;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.ohue.medmanager.helper.Instantiater;

import itoo.ohue.medmanager.R;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.VIew view;
    private Context context;

    public SearchPresenter(Context context, SearchContract.VIew view) {
            this.context = context;
            this.view = view;
    }

    @Override
    public void search(String nameQuery) {
        if(TextUtils.isEmpty(nameQuery)) {
            view.makeToast(R.string.field_error);
        }
        else {
           if(searchQuery(nameQuery).getCount() >0) {
               view.showSearchResults(searchQuery(nameQuery));
           }
           else {
               view.makeToast("no search result");
           }
        }

    }

    public Cursor searchQuery(String nameQuery) {
        return Instantiater.getDBOps(context , Instantiater.getDBInstance(context)).queryName(nameQuery);
    }

}
