package com.ohue.medmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ohue.medmanager.models.Medication;

public class MedDBOps {
    /**
     * This class contains helper methods for
     * DB CRUD operations;
     */
   private Context context;
   private SQLiteDatabase db;
    public MedDBOps(Context context , SQLiteDatabase db){
        this.context = context;
        this.db = db;
    }

    public void destroy () {
        context = null;
        db  = null;
    }

    public long addMedication(Medication medication) {
        ContentValues cv = new ContentValues();
        cv.put(MedDBContract.MedicationColumns.MED_NAME, medication.getName());
        cv.put(MedDBContract.MedicationColumns.COLUMN_MED_DATE , medication.getTimeToTake());
        cv.put(MedDBContract.MedicationColumns.COLUMN_MED_SPECIFIC_ID, medication.getUniqueKey());
        cv.put(MedDBContract.MedicationColumns.COLUMN_MED_DETAIlS, medication.getDetails());
        cv.put(MedDBContract.MedicationColumns.COLUMN_MED_END_DATE, medication.getEndDate());
        return db.insert(MedDBContract.MedicationColumns.TABLE_NAME , null , cv);
    }

    public  Cursor queryAllReminders() {
        return db.query(MedDBContract.MedicationColumns.TABLE_NAME,
                null,
                null,
                null,
                null,
                null, MedDBContract.MedicationColumns.COLUMN_MED_DATE+ " ASC"
        );
    }

    public Cursor queryRemindersInOrder() {
              String queryString = "SELECT * FROM " + MedDBContract.MedicationColumns.TABLE_NAME + " ORDER BY " + MedDBContract.MedicationColumns.COLUMN_MED_DATE + " DESC ";
              return db.rawQuery(queryString, null);

    }

    public Cursor queryName(String nameQuery) {

              String selectQuery = "SELECT  * FROM " +  MedDBContract.MedicationColumns.TABLE_NAME + " WHERE "
                      + MedDBContract.MedicationColumns.MED_NAME + " = '" + nameQuery +  "'";
              return db.rawQuery(selectQuery, null);

    }

    public Cursor queryforMonth(String monthString) {
//        String where = "strftime('%m', '"+C+"') = " +
//                "'"+monthString+"'";

              String selectQuery = "SELECT  * FROM " +  MedDBContract.MedicationColumns.TABLE_NAME + " WHERE "
                      + " strftime('%m',"+  MedDBContract.MedicationColumns.COLUMN_MED_DATE + " )" +  " = '" + monthString +  "'";

              return  db.rawQuery(selectQuery, null);
    }

}