package com.example.shashank.sha;

import android.provider.BaseColumns;

/**
 * Created by shashank on 15-10-2015.
 */
public final class DatabaseContract {
    DatabaseContract(){
        //Empty Constructor
    }

    public  static abstract class DatabaseEntry implements BaseColumns{

        public static final String TABLE_NAME_BASIC_DETAILS = "basicdetails";
        public static final String COLUMN_NAME_MIN_ATTENDANCE = "minattendance";
        public static final String COLUMN_NAME_NO_OF_WORKING_DAYS = "noofworkingdays";

        public static final String TABLE_NAME_WEEK_DAYS = "weekdays";
        public static final String COLUMN_NAME_MONDAY = "monday";
        public static final String COLUMN_NAME_TUESDAY = "tuesday";
        public static final String COLUMN_NAME_WEDNESDAY = "wednesday";
        public static final String COLUMN_NAME_THURSDAY = "thursday";
        public static final String COLUMN_NAME_FRIDAY = "friday";
        public static final String COLUMN_NAME_SATURDAY = "saturday";

        public static final String TABLE_NAME_TOT_CLASSES = "totclasses";
        public static final String COLUMN_NAME_TOT_PRESENT = "totheld";
        public static final String COLUMN_NAME_TOT_HELD = "totpresent";

    }
}
