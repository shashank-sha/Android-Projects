package com.example.shashank.sha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    SharedPreferences sharedPreferences;
    TextView AttendanceTextView,minAttendance;
    Button addAttendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //sharedPreferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor=sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DatabaseTotClassesHelper totClassesHelper=new DatabaseTotClassesHelper(this);
        sqLiteDatabase=totClassesHelper.getReadableDatabase();
        String[] projection = {
                DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_HELD,
                DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT

        };
        Cursor cursor=sqLiteDatabase.query(DatabaseContract.DatabaseEntry.TABLE_NAME_TOT_CLASSES, projection, null, null, null, null, null);
        cursor.moveToFirst();
        int tot_held= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_HELD));
        int tot_pre=cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT));
        //Log.d("aaaaaaaaaaaaaa", tot_held + " " + tot_pre);
        //Toast.makeText(getApplicationContext(), tot_held + " " + tot_pre, Toast.LENGTH_LONG);

        double attendance=Attendance.calculateAttandance(tot_held,tot_pre);
        AttendanceTextView=(TextView) findViewById(R.id.attendancetextView);
        AttendanceTextView.setText(new DecimalFormat("##.##").format(attendance));



        DatabaseBasicDetailsHelper databaseBasicDetailsHelper=new DatabaseBasicDetailsHelper(this);
        sqLiteDatabase=databaseBasicDetailsHelper.getReadableDatabase();
        String[] projection1 = {
                DatabaseContract.DatabaseEntry.COLUMN_NAME_MIN_ATTENDANCE,
                DatabaseContract.DatabaseEntry.COLUMN_NAME_NO_OF_WORKING_DAYS

        };
        cursor=sqLiteDatabase.query(DatabaseContract.DatabaseEntry.TABLE_NAME_BASIC_DETAILS,projection1,null,null,null,null,null);
        cursor.moveToFirst();
        int minAtt=cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_MIN_ATTENDANCE));

        minAttendance=(TextView) findViewById(R.id.reach75TextView);
        if(attendance<minAtt)
            minAttendance.setText("When Will It Cross "+minAtt+"% ?");
        else if(attendance>minAtt)
            minAttendance.setText("When Will It Come Down To "+minAtt+"% ?");
        else
            minAttendance.setText("You Are On The Edge");


        addAttendance = (Button) findViewById(R.id.add_Todays_Attendance_Button);
        addAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTodaysAttendance.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
