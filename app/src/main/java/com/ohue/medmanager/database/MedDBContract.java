package com.ohue.medmanager.database;

import android.provider.BaseColumns;

public class MedDBContract {
    /**
     * This contract class hosts The table containing our db
     * values;
     */

    //required empty constructor
    public  MedDBContract(){}

    public static class MedicationColumns implements BaseColumns {
        /**
         * Table with the strings representing the columns
         */
        public static final String TABLE_NAME = "medicationTable";
        public static final String MED_NAME = "medicationName";
        public static final String COLUMN_MED_SPECIFIC_ID = "medSpecificId";
        public static final String COLUMN_MED_DETAIlS = "medDetails";
        public static final String COLUMN_MED_DATE = "startDate";

    }


}
