package com.ohue.medmanager.category;

import android.content.Context;
import android.database.Cursor;

import com.ohue.medmanager.helper.Instantiater;

public class CategoryPresenter implements CategoryContract.Presenter {
    Context context;
    public CategoryPresenter(Context context) {
        this.context = context;
    }
    @Override
    public Cursor getMedsForMonth(int month) {
        String monthString ="";
        if(month < 10) {
            monthString = "0" + String.valueOf(month);
        }
        else{
            monthString = String.valueOf(month);
        }
       return Instantiater.getDBOps(context, Instantiater.getDBInstance(context)).queryforMonth(monthString);
        }
}