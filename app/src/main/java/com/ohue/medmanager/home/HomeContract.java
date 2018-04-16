package com.ohue.medmanager.home;

import com.ohue.medmanager.base.BasePresenter;
import com.ohue.medmanager.base.BaseView;

import java.util.Calendar;

import com.ohue.medmanager.models.Medication;

public interface HomeContract {

    interface View extends BaseView<Presenter> {
        void medicationAdded(boolean isAdded);

        void setTimeText(String time);

        void setDateText(String date);
    }

   interface Presenter extends BasePresenter {
       void addMedication(Medication medication);

       void showTimeDialog(boolean isTime);

       void provideCalendar (Calendar calendar);

       Calendar getCalendar ();
    }
}

