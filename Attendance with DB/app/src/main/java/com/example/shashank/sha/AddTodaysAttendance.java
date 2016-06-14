package com.example.shashank.sha;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTodaysAttendance extends AppCompatActivity {

    Button addTodaysAttendance;
    EditText todaysAttendance;
    int noOfClassesToday,tot_held,tot_pre_read,tot_pre;
    String weekDay;
    SQLiteDatabase sqLiteDatabase,sqLiteDatabaseUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todays_attendance);


        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);//E for Mon
        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());
        //weekDay=weekDay.toLowerCase();
        //Toast.makeText(AddTodaysAttendance.this, weekDay, Toast.LENGTH_LONG).show();


        final DatabaseWeekDaysHelper databaseWeekDaysHelper = new DatabaseWeekDaysHelper(this);
        sqLiteDatabase = databaseWeekDaysHelper.getReadableDatabase();
        String[] projection = {
                DatabaseContract.DatabaseEntry.COLUMN_NAME_MONDAY,
                DatabaseContract.DatabaseEntry.COLUMN_NAME_TUESDAY,
                DatabaseContract.DatabaseEntry.COLUMN_NAME_WEDNESDAY,
                DatabaseContract.DatabaseEntry.COLUMN_NAME_THURSDAY,
                DatabaseContract.DatabaseEntry.COLUMN_NAME_FRIDAY,
                DatabaseContract.DatabaseEntry.COLUMN_NAME_SATURDAY,

        };
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.DatabaseEntry.TABLE_NAME_WEEK_DAYS, projection, null, null, null, null, null);
        cursor.moveToFirst();

        if (weekDay.equalsIgnoreCase("Sunday")) {
            Toast.makeText(AddTodaysAttendance.this, "Today is sunday", Toast.LENGTH_LONG).show();

        } else {
            if (weekDay.equalsIgnoreCase("Monday")) {
                noOfClassesToday = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_MONDAY));
            }
            if (weekDay.equalsIgnoreCase("Tuesday")) {
                noOfClassesToday = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_TUESDAY));
            }
            if (weekDay.equalsIgnoreCase("Wednesday")) {
                noOfClassesToday = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_WEDNESDAY));
            }
            if (weekDay.equalsIgnoreCase("Thursday")) {
                noOfClassesToday = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_THURSDAY));
            }
            if (weekDay.equalsIgnoreCase("Friday")) {
                noOfClassesToday = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_FRIDAY));
            }
            if (weekDay.equalsIgnoreCase("Saturday")) {
                noOfClassesToday = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_SATURDAY));
            }

            Toast.makeText(AddTodaysAttendance.this, "Maximum of " + noOfClassesToday, Toast.LENGTH_LONG).show();


        /*
        * ********************************************************************************
        * *********   Retrieving values from DatabaseTotClassesHelper   ******************
        * ********************************************************************************
        */


            final DatabaseTotClassesHelper totClassesHelper = new DatabaseTotClassesHelper(this);
            sqLiteDatabase = totClassesHelper.getReadableDatabase();
            String[] projectionTotClasses = {
                    DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_HELD,
                    DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT

            };
            Cursor cursorTotClasses = sqLiteDatabase.query(DatabaseContract.DatabaseEntry.TABLE_NAME_TOT_CLASSES, projectionTotClasses, null, null, null, null, null);
            cursorTotClasses.moveToFirst();
            tot_held = cursorTotClasses.getInt(cursorTotClasses.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_HELD));
            tot_pre_read = cursorTotClasses.getInt(cursorTotClasses.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT));

            sqLiteDatabase.close();

        /*
        * ********************************************************************************
        * ****************     Updating when button is clicked     ***********************
        * ********************************************************************************
        */

            sqLiteDatabaseUpdate = totClassesHelper.getReadableDatabase();
            addTodaysAttendance = (Button) findViewById(R.id.addTodaysAttendanceButton);
            todaysAttendance = (EditText) findViewById(R.id.todaysAttendanceEditText);
            addTodaysAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("zzzzzzzzzzzzzzzzzzz", "1231111111111111111111111111111111123");
                    tot_held = tot_held + noOfClassesToday;
                    tot_pre = tot_pre_read + Integer.parseInt(todaysAttendance.getText().toString());

                    ContentValues values = new ContentValues();
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_HELD, tot_held);
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT, tot_pre);

                    Log.d("mmmmmmmmmmmmmmmmmmmmmm", "99999999999999999999999999999");

                    String selection = DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT + " LIKE ?";
                    String[] selectionArgs = {String.valueOf(tot_pre)};

                    int count = sqLiteDatabaseUpdate.update(
                            DatabaseContract.DatabaseEntry.TABLE_NAME_TOT_CLASSES,
                            values,
                            selection,
                            selectionArgs);

                    Log.d("xxxxxxxxxxxxxxxxxxxxxx", "4444444444444444444444444444444444");

                    Toast.makeText(AddTodaysAttendance.this, count, Toast.LENGTH_LONG).show();
                }
            });
        }// end of else
    }//end of onCreate
}//end of class
