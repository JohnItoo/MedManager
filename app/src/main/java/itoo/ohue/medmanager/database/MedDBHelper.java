package itoo.ohue.medmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedDBHelper extends SQLiteOpenHelper {
    private static final int MED_DB_VERSION = 1;
    private static  final String MED_DB_NAME = "med_manager.db";

    public MedDBHelper(Context context) {
        super(context, MED_DB_NAME , null , MED_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + MedDBContract.MedicationColumns.TABLE_NAME + " (" +
                MedDBContract.MedicationColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                MedDBContract.MedicationColumns.MED_NAME + " TEXT NOT NULL, "+
                MedDBContract.MedicationColumns.COLUMN_MED_SPECIFIC_ID + " TEXT NOT NULL, "+
                MedDBContract.MedicationColumns.COLUMN_MED_DATE + " TEXT NOT NULL, " +
                MedDBContract.MedicationColumns.COLUMN_MED_DETAIlS +  " TEXT NOT NULL " +
                ");";
        db.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
