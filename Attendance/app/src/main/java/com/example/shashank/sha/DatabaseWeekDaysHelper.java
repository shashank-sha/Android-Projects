package com.example.shashank.sha;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shashank on 15-10-2015.
 */
public class DatabaseWeekDaysHelper extends SQLiteOpenHelper{
private static final String INTEGER_TYPE = " INTEGER";
private static final String COMMA_SEP = ",";
private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + DatabaseContract.DatabaseEntry.TABLE_NAME_WEEK_DAYS + " (" +
                DatabaseContract.DatabaseEntry.COLUMN_NAME_MONDAY + INTEGER_TYPE + COMMA_SEP +
                DatabaseContract.DatabaseEntry.COLUMN_NAME_TUESDAY + INTEGER_TYPE + COMMA_SEP +
                DatabaseContract.DatabaseEntry.COLUMN_NAME_WEDNESDAY + INTEGER_TYPE + COMMA_SEP +
                DatabaseContract.DatabaseEntry.COLUMN_NAME_THURSDAY + INTEGER_TYPE + COMMA_SEP +
                DatabaseContract.DatabaseEntry.COLUMN_NAME_FRIDAY + INTEGER_TYPE + COMMA_SEP +
                DatabaseContract.DatabaseEntry.COLUMN_NAME_SATURDAY + INTEGER_TYPE +
                " )";

private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + DatabaseContract.DatabaseEntry.TABLE_NAME_WEEK_DAYS;

public static final int DATABASE_VERSION_WEEK_DAYS = 1;
public static final String DATABASE_NAME_WEEK_DAYS = "weekdays.db";
public static final int DATABASE_VERSION_TOT_CLASSES = 1;
public static final String DATABASE_NAME_TOT_CLASSES = "totclasses.db";

        public DatabaseWeekDaysHelper(Context context){
            super(context, DATABASE_NAME_WEEK_DAYS, null, DATABASE_VERSION_WEEK_DAYS);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

}
