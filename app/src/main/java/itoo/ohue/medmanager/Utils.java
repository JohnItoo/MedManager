package itoo.ohue.medmanager;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import itoo.ohue.medmanager.models.Medication;

public class Utils {

    public  static String formatDate(Date dateToFormat , boolean isDate){
        if(isDate) {
            DateFormat displayFormat = new SimpleDateFormat("E,  MMMM d,  yyyy", Locale.ENGLISH);
            return displayFormat.format(dateToFormat);
        }
        else{
            DateFormat displayFormat = new SimpleDateFormat("HH : mm ", Locale.ENGLISH);
            return displayFormat.format(dateToFormat);
        }
    }
    public static String databaseDateFormat(Date dateToFormat){
        DateFormat    databaseFormat =  new SimpleDateFormat("E,  MMMM d,  yyyy , HH : mm", Locale.ENGLISH);
        return  databaseFormat.format(dateToFormat);
    }
    public static Calendar dateToCalendar(String dateInStrings){
        DateFormat    databaseFormat =  new SimpleDateFormat("E,  MMMM d,  yyyy , HH : mm", Locale.ENGLISH);
        Date date = Calendar.getInstance().getTime();
        try {
            date = databaseFormat.parse(dateInStrings);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Notification getNotification ( Medication medication , Context context){
        Notification.Builder builder = new Notification.Builder(context)

                .setContentTitle(medication.getName())
                .setContentText(medication.getDetails())
                .setSmallIcon(R.drawable.ic_menu_send)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        return builder.build();
    }
}
