package com.ohue.medmanager.overview;

import android.content.Context;
import android.database.Cursor;

import com.ohue.medmanager.helper.Instantiater;

public class OverviewPresenter implements OverviewContract.Presenter {
    private Context context;

    public OverviewPresenter(Context context ) {
        this.context = context;
    }


    @Override
    public Cursor getAllMeds() {
        return Instantiater.getDBOps(context , Instantiater.getDBInstance(context)).queryAllReminders();
    }
}
