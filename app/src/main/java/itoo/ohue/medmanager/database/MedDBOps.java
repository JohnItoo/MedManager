package itoo.ohue.medmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import itoo.ohue.medmanager.helper.Instantiater;
import itoo.ohue.medmanager.models.Medication;

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
        cv.put(MedDBContract.MedicationColumns.COLUMN_MED_DETAIlS, "Nothing yet");
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

    public Cursor queryName(String nameQuery) {
        return db.query(MedDBContract.MedicationColumns.TABLE_NAME,
                new String[] {MedDBContract.MedicationColumns.MED_NAME, MedDBContract.MedicationColumns.COLUMN_MED_DATE,
                        MedDBContract.MedicationColumns.COLUMN_MED_DETAIlS},
                MedDBContract.MedicationColumns.MED_NAME + "=?", new String[] { String.valueOf(nameQuery) },
                null,
                null,
                null,
                null
                );
    }

}