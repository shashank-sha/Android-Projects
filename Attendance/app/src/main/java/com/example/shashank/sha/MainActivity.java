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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    SharedPreferences sharedPreferences;
    TextView AttendanceTextView,minAttendance;
    Button addAttendance;
    int tot_held,tot_pre,minAtt,mon,tue,wed,thu,fri,sat,weekDayNum;
    double attendance;
    String weekDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
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



        tot_held=sharedPreferences.getInt("Total_Held",1);
        tot_pre=sharedPreferences.getInt("Total_Present", 1);

        attendance=Attendance.calculateAttandance(tot_held,tot_pre);

        AttendanceTextView=(TextView) findViewById(R.id.attendancetextView);
        AttendanceTextView.setText(new DecimalFormat("##.##").format(attendance));




        minAtt=sharedPreferences.getInt("Minimum_Percentge",1);

        minAttendance=(TextView) findViewById(R.id.reach75TextView);
        if(attendance<minAtt)
            minAttendance.setText("When Will It Cross "+minAtt+"% ?");
        else if(attendance>minAtt)
            minAttendance.setText("When Will It Come Down To "+minAtt+"% ?");
        else
            minAttendance.setText("You Are On The Edge");


        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);//E for Mon
        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());

        if (weekDay.equalsIgnoreCase("Monday"))
            weekDayNum=0;
        else if (weekDay.equalsIgnoreCase("Tuesday"))
            weekDayNum=1;
        else if (weekDay.equalsIgnoreCase("Wednesday"))
            weekDayNum=2;
        else if (weekDay.equalsIgnoreCase("Thursday"))
            weekDayNum=3;
        else if (weekDay.equalsIgnoreCase("Friday"))
            weekDayNum=4;
        else if (weekDay.equalsIgnoreCase("Saturday"))
            weekDayNum=5;


        mon=sharedPreferences.getInt("Mon", 1);
        tue=sharedPreferences.getInt("Tue", 1);
        wed=sharedPreferences.getInt("Wed", 1);
        thu=sharedPreferences.getInt("Thu", 1);
        fri=sharedPreferences.getInt("Fri", 1);
        sat=sharedPreferences.getInt("Sat", 1);


        final int[] classesEveryDay={mon,tue,wed,thu,fri,sat,0};


        addAttendance = (Button) findViewById(R.id.add_Todays_Attendance_Button);
        addAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (weekDay.equalsIgnoreCase("Sunday")) {
                    Toast.makeText(MainActivity.this,"Today is sunday", Toast.LENGTH_LONG).show();
                }

                else {
                    Intent intent = new Intent(MainActivity.this, AddTodaysAttendance.class);
                    startActivity(intent);
                }
            }
        });


        minAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(attendance<minAtt){
                    int days=Attendance.calculateDaysCrossing75(tot_held,tot_pre,classesEveryDay,weekDayNum,minAtt);
                    Toast.makeText(MainActivity.this, "attend more "+days, Toast.LENGTH_LONG).show();
                }

                else if(attendance>minAtt) {
                    int days = Attendance.calculateDaysDropping75(tot_held, tot_pre, classesEveryDay, weekDayNum, minAtt);
                    Toast.makeText(MainActivity.this, "have more"+days, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this, "On 75", Toast.LENGTH_LONG).show();
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
