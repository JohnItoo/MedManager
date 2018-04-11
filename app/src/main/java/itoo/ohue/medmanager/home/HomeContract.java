package itoo.ohue.medmanager.home;

import java.util.Calendar;
import java.util.Map;

import itoo.ohue.medmanager.base.BasePresenter;
import itoo.ohue.medmanager.base.BaseView;
import itoo.ohue.medmanager.models.Medication;

public interface HomeContract {

    interface View extends BaseView<HomeContract.Presenter> {
        void medicationAdded( boolean isAdded );



        void setTimeText( String time);

        void setDateText(String date);


    }

   interface Presenter extends BasePresenter {
       void addMedication(Medication medication);

       void showTimeDialog(boolean isTime);

       void provideCalendar (Calendar calendar);

       Calendar getCalendar ();
    }
}

