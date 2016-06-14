package com.example.shashank.sha;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by shashank on 15-10-2015.
 */
public class DatabaseTotClassesHelper extends SQLiteOpenHelper {
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
     static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.DatabaseEntry.TABLE_NAME_TOT_CLASSES + " (" +
                    DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_HELD + INTEGER_TYPE + COMMA_SEP +
                    DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT + INTEGER_TYPE  +
                    " )";




    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.DatabaseEntry.TABLE_NAME_TOT_CLASSES;


    public static final int DATABASE_VERSION_TOT_CLASSES = 1;
    public static final String DATABASE_NAME_TOT_CLASSES = "totclasses.db";

    public DatabaseTotClassesHelper(Context context){
        super(context, DATABASE_NAME_TOT_CLASSES, null, DATABASE_VERSION_TOT_CLASSES);
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
