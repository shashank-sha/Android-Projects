package com.example.shashank.sha;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BeforeMain3 extends AppCompatActivity {

    int mon,tue,wed,thu,fri,sat;
    EditText EditTextmon,EditTexttue,EditTextwed,EditTextthu,EditTextfri,EditTextsat;
    Button done;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_main3);

        mon=getIntent().getExtras().getInt("mon");
        tue=getIntent().getExtras().getInt("tue");
        wed=getIntent().getExtras().getInt("wed");
        thu=getIntent().getExtras().getInt("thu");
        fri=getIntent().getExtras().getInt("fri");
        sat=getIntent().getExtras().getInt("sat");
        //Toast.makeText(getApplicationContext(), mon + " " + tue+ " " + wed+ " " + thu+ " " + fri+ " " + sat, Toast.LENGTH_LONG).show();

        EditTextmon = (EditText) findViewById(R.id.editTextMon);
        EditTexttue = (EditText) findViewById(R.id.editTextTue);
        EditTextwed = (EditText) findViewById(R.id.editTextWed);
        EditTextthu = (EditText) findViewById(R.id.editTextThu);
        EditTextfri = (EditText) findViewById(R.id.editTextFri);
        EditTextsat = (EditText) findViewById(R.id.editTextSat);

        if(mon==0){
            EditTextmon.setText("0");
            EditTextmon.setFocusable(false);
        }
        if(tue==0){
            EditTexttue.setText("0");
            EditTexttue.setFocusable(false);
        }
        if(wed==0){
            EditTextwed.setText("0");
            EditTextwed.setFocusable(false);
        }
        if(thu==0){
            EditTextthu.setText("0");
            EditTextthu.setFocusable(false);
        }
        if(fri==0){
            EditTextfri.setText("0");
            EditTextfri.setFocusable(false);
        }
        if(sat==0){
            EditTextsat.setText("0");
            EditTextsat.setFocusable(false);
        }

        done=(Button) findViewById(R.id.doneButton);
        final DatabaseWeekDaysHelper databaseWeekDaysHelper=new DatabaseWeekDaysHelper(this);
        sqLiteDatabase=databaseWeekDaysHelper.getWritableDatabase();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EditTextmon.getText().toString().matches("")
                        ||EditTexttue.getText().toString().matches("")
                        ||EditTextwed.getText().toString().matches("")
                        ||EditTextthu.getText().toString().matches("")
                        ||EditTextfri.getText().toString().matches("")
                        ||EditTextsat.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Text fields cannot be empty", Toast.LENGTH_LONG).show();
                }
                else {
                    /*ContentValues values = new ContentValues();
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_MONDAY, Integer.parseInt(EditTextmon.getText().toString()));
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_TUESDAY, Integer.parseInt(EditTexttue.getText().toString()));
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_WEDNESDAY, Integer.parseInt(EditTextwed.getText().toString()));
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_THURSDAY, Integer.parseInt(EditTextthu.getText().toString()));
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_FRIDAY, Integer.parseInt(EditTextfri.getText().toString()));
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_SATURDAY, Integer.parseInt(EditTextsat.getText().toString()));

                    sqLiteDatabase.insert(
                            DatabaseContract.DatabaseEntry.TABLE_NAME_WEEK_DAYS,
                            null,
                            values);

                    /*
                /*
                sqLiteDatabase=databaseWeekDaysHelper.getReadableDatabase();
                String[] projection = {
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_MONDAY,
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_TUESDAY,
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_WEDNESDAY,
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_THURSDAY,
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_FRIDAY,
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_SATURDAY,

                };
                Cursor cursor=sqLiteDatabase.query(DatabaseContract.DatabaseEntry.TABLE_NAME_WEEK_DAYS,projection,null,null,null,null,null);
                cursor.moveToFirst();
                int mo= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_MONDAY));
                int tu= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_TUESDAY));
                int we= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_WEDNESDAY));
                int th= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_THURSDAY));
                int fr= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_FRIDAY));
                int sa= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_SATURDAY));

                Log.d("aaaaaaaaaaaaaa", mo + " " + tu+ " " + we+ " " + th+ " " + fr+ " " + sa);
                Toast.makeText(getApplicationContext(), mo + " " + tu+ " " + we+ " " + th+ " " + fr+ " " + sa, Toast.LENGTH_LONG).show();
            */

                    SharedPreferences sharedPreferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("first", false);
                    editor.putInt("Mon", Integer.parseInt(EditTextmon.getText().toString()));
                    editor.putInt("Tue", Integer.parseInt(EditTexttue.getText().toString()));
                    editor.putInt("Wed", Integer.parseInt(EditTextwed.getText().toString()));
                    editor.putInt("Thu", Integer.parseInt(EditTextthu.getText().toString()));
                    editor.putInt("Fri", Integer.parseInt(EditTextfri.getText().toString()));
                    editor.putInt("Sat", Integer.parseInt(EditTextsat.getText().toString()));
                    editor.commit();

                    Intent intent = new Intent(BeforeMain3.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
