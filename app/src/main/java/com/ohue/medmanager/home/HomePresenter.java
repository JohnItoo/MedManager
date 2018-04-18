package com.ohue.medmanager.home;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

import com.ohue.medmanager.miscellanous.AlarmReceiver;
import itoo.ohue.medmanager.R;
import com.ohue.medmanager.miscellanous.Utils;
import com.ohue.medmanager.helper.Instantiater;
import com.ohue.medmanager.models.Medication;

public class HomePresenter  implements HomeContract.Presenter {
    private HomeContract.View view;
    private Context context;
    private Calendar calendar;
    private Calendar endMedCalendar;



    public HomePresenter (HomeContract.View view , Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void addMedication( Medication medication ) {
        validateStrings(medication );
    }

    @Override
    public void showDialog(boolean isTime, boolean isStartingDialog) {
        createDialog(isTime, isStartingDialog);
    }

    // Method to ensure validity of input Strings

    private void validateStrings( Medication medication ) {

            if (TextUtils.isEmpty(medication.getName()) || TextUtils.isEmpty(medication.getTimeToTake())) {
                view.makeToast(R.string.field_error);
                return;
            }
            else if (Utils.dateToCalendar(medication.getTimeToTake()).compareTo(Calendar.getInstance()) <0 ) {
                view.makeToast(R.string.time_has_passed);
                return;
            }
            else if (Utils.dateToCalendar(medication.getEndDate()).compareTo(Calendar.getInstance()) <0){
                view.makeToast(R.string.time_has_passed);
                return;
            }
            regMed(medication);
            }

    // This method  inserts medication into db
    private void regMed(Medication medication) {
        long uniqueLong = System.currentTimeMillis();
        String uniqueKey = String.valueOf(uniqueLong);
        medication.setUniqueKey(uniqueKey);
        triggerMedAlarm(medication , Utils.getNotification(medication,context), (int) uniqueLong);
      if  (Instantiater.getDBOps(context , Instantiater.getDBInstance(context)).addMedication(medication) >= 0) {
          view.medicationAdded(true);
      }
      else{
          view.medicationAdded(false);
      }
    }

    @Override
    public void provideCalendar(Calendar calendar) {
       this.calendar = (Calendar) calendar.clone();
       this.endMedCalendar = (Calendar) calendar.clone();
    }

    @Override
    public Calendar getCalendar() {
        return this.calendar;
    }

    @Override
    public Calendar getMedEndCalendat() {
        return this.endMedCalendar;
    }


    //This method handles crating dialiogs for both the Beginning of medication cycle and the end
    private void createDialog (boolean isTime, final boolean isStartingDialog){
        final java.util.Calendar dateCal = java.util.Calendar.getInstance();
        final Calendar secondCalendar = Calendar.getInstance();
        final java.util.Calendar dateCalendar = java.util.Calendar.getInstance();
        int yearPresent = dateCal.get(java.util.Calendar.YEAR);
        int monthPresent = dateCal.get(java.util.Calendar.MONTH);
        int dayPresent  = dateCal.get(java.util.Calendar.DAY_OF_MONTH);
        int hourOfDay = dateCal.get(Calendar.HOUR_OF_DAY);
        int minute = dateCal.get(Calendar.MINUTE);

        if(!isTime){
            DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    //block handling Starting Date for Medication
                    if(isStartingDialog) {
                        dateCal.set(i, i1, i2);
                        String dateString = Utils.formatDate(dateCal.getTime(), true);
                        calendar.set(i, i1, i2);
                        view.setDateText(dateString);
                    }// block handling EndDate for Medication
                    else{
                        secondCalendar.set(i, i1, i2);
                        String endDateString = Utils.formatDate(secondCalendar.getTime(), true);
                        endMedCalendar.set(i, i1, i2);
                        view.setEndMedDateText(endDateString);

                    }

                }
            }, yearPresent,monthPresent,dayPresent);
            datePickerDialog.show();
        }
        else{
            TimePickerDialog timePickerDialog = new TimePickerDialog(context ,  new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    if (isStartingDialog) {
                        dateCal.set(Calendar.HOUR_OF_DAY, i);
                        dateCal.set(Calendar.MINUTE, i1);
                        dateCal.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.HOUR_OF_DAY, i);
                        calendar.set(Calendar.MINUTE, i1);
                        calendar.set(Calendar.SECOND, 0);
                        String timeString = Utils.formatDate(calendar.getTime(), false);
                        view.setTimeText(timeString);
                    } else {
                        secondCalendar.set(Calendar.HOUR_OF_DAY, i);
                        secondCalendar.set(Calendar.MINUTE, i1);
                        secondCalendar.set(Calendar.SECOND, 0);
                        endMedCalendar.set(Calendar.HOUR_OF_DAY, i);
                        endMedCalendar.set(Calendar.MINUTE, i1);
                        endMedCalendar.set(Calendar.SECOND, 0);
                        String endMedTimeString = Utils.formatDate(endMedCalendar.getTime(), false);
                        view.setEndMedTimeText(endMedTimeString);
                        }
                }
            },hourOfDay,minute,false);
            timePickerDialog.show();
        }
    }
    private void triggerMedAlarm(Medication medication, Notification notification, int i) {
        Intent notificationIntent = new Intent(context,  AlarmReceiver.class);
       Calendar medCalendar = Utils.dateToCalendar(medication.getTimeToTake());
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, i);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, medCalendar.getTimeInMillis(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, medCalendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, medCalendar.getTimeInMillis(), pendingIntent);
        }
    }

}
