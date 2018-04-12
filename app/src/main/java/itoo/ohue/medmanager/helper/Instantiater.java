package itoo.ohue.medmanager.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import itoo.ohue.medmanager.database.MedDBHelper;
import itoo.ohue.medmanager.database.MedDBOps;

public class Instantiater {
    /**
     * This class ensures that there
     * is only one instance of ita child repositories
     * In order to avoid memory abuse
     */
    private static MedDBOps dbOps = null;
    private static SQLiteDatabase db = null;


    //region DBOPs

    public static MedDBOps getDBOps (Context context , SQLiteDatabase db) {
        if (dbOps == null ) {
            return new MedDBOps( context , db);
        }
        return  dbOps;
    }

    public  static void  destroyDbOPs() {
        if(dbOps!= null) {
            dbOps.destroy();
        }
    }
    //endregion

    //region DB
     public static SQLiteDatabase getDBInstance(Context ctx) {
        if(db == null) {
            MedDBHelper helper = new MedDBHelper(ctx);
            db =  helper.getWritableDatabase();
            return db;
        }
        return db;
     }
    //endregion




}
