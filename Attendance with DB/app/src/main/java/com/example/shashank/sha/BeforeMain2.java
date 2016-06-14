package com.example.shashank.sha;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BeforeMain2 extends AppCompatActivity {

    private Button main2Next;
    private TextView mon,tue,wed,thu,fri,sat;
    private ImageView iMonU,iTueU,iWedU,iThuU,iFriU,iSatU; //unset images
    private ImageView iMon,iTue,iWed,iThu,iFri,iSat;       //set images
    int noOfWorkingDays=0,minAttendance;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_main2);

        minAttendance = getIntent().getExtras().getInt("minAttendance");
        // Toast.makeText(getApplicationContext(), minAttendance+"empty", Toast.LENGTH_LONG).show();

        main2Next=(Button) findViewById(R.id.nextButton2);
        mon=(TextView) findViewById(R.id.textViewMon);
        tue=(TextView) findViewById(R.id.textViewTue);
        wed=(TextView) findViewById(R.id.textViewWed);
        thu=(TextView) findViewById(R.id.textViewThurs);
        fri=(TextView) findViewById(R.id.textViewFri);
        sat=(TextView) findViewById(R.id.textViewSat);

        iMonU=(ImageView) findViewById(R.id.imgMonUnSet);
        iTueU=(ImageView) findViewById(R.id.imgTuesUnSet);
        iWedU=(ImageView) findViewById(R.id.imgWedUnSet);
        iThuU=(ImageView) findViewById(R.id.imgThursUnSet);
        iFriU=(ImageView) findViewById(R.id.imgFriUnSet);
        iSatU=(ImageView) findViewById(R.id.imgSatUnSet);

        iMon=(ImageView) findViewById(R.id.imgMonSet);
        iTue=(ImageView) findViewById(R.id.imgTuesSet);
        iWed=(ImageView) findViewById(R.id.imgWedSet);
        iThu=(ImageView) findViewById(R.id.imgThursSet);
        iFri=(ImageView) findViewById(R.id.imgFriSet);
        iSat=(ImageView) findViewById(R.id.imgSatSet);

        final DatabaseBasicDetailsHelper databaseBasicDetailsHelper=new DatabaseBasicDetailsHelper(this);
        sqLiteDatabase=databaseBasicDetailsHelper.getWritableDatabase();

        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility;
                visibility=iMonU.getVisibility();
                if(visibility== View.VISIBLE){
                    iMonU.setVisibility(View.GONE);
                    iMon.setVisibility(View.VISIBLE);
                }
                else{
                    iMonU.setVisibility(View.VISIBLE);
                    iMon.setVisibility(View.GONE);
                }
            }
        });

        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility;
                visibility=iTueU.getVisibility();
                if(visibility== View.VISIBLE){
                    iTueU.setVisibility(View.GONE);
                    iTue.setVisibility(View.VISIBLE);
                }
                else{
                    iTueU.setVisibility(View.VISIBLE);
                    iTue.setVisibility(View.GONE);
                }
            }
        });

        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility;
                visibility=iWedU.getVisibility();
                if(visibility== View.VISIBLE){
                    iWedU.setVisibility(View.GONE);
                    iWed.setVisibility(View.VISIBLE);
                }
                else{
                    iWedU.setVisibility(View.VISIBLE);
                    iWed.setVisibility(View.GONE);
                }
            }
        });

        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility;
                visibility=iThuU.getVisibility();
                if(visibility== View.VISIBLE){
                    iThuU.setVisibility(View.GONE);
                    iThu.setVisibility(View.VISIBLE);
                }
                else{
                    iThuU.setVisibility(View.VISIBLE);
                    iThu.setVisibility(View.GONE);
                }
            }
        });

        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility;
                visibility=iFriU.getVisibility();
                if(visibility== View.VISIBLE){
                    iFriU.setVisibility(View.GONE);
                    iFri.setVisibility(View.VISIBLE);
                }
                else{
                    iFriU.setVisibility(View.VISIBLE);
                    iFri.setVisibility(View.GONE);
                }
            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility;
                visibility=iSatU.getVisibility();
                if(visibility== View.VISIBLE){
                    iSatU.setVisibility(View.GONE);
                    iSat.setVisibility(View.VISIBLE);
                }
                else{
                    iSatU.setVisibility(View.VISIBLE);
                    iSat.setVisibility(View.GONE);
                }
            }
        });

        final Intent intent=new Intent(BeforeMain2.this,BeforeMain3.class);

        main2Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noOfWorkingDays=0;
                if(iMon.getVisibility()==View.VISIBLE){
                    noOfWorkingDays+=1;
                    intent.putExtra("mon",1);
                }
                else
                    intent.putExtra("mon",0);
                if(iTue.getVisibility()==View.VISIBLE){
                    noOfWorkingDays+=1;
                    intent.putExtra("tue",1);
                }
                else
                    intent.putExtra("tue",0);
                if(iWed.getVisibility()==View.VISIBLE){
                    noOfWorkingDays+=1;
                    intent.putExtra("wed",1);
                }
                else
                    intent.putExtra("wed",0);
                if(iThu.getVisibility()==View.VISIBLE){
                    noOfWorkingDays+=1;
                    intent.putExtra("thu",1);
                }
                else
                    intent.putExtra("thu",0);
                if(iFri.getVisibility()==View.VISIBLE){
                    noOfWorkingDays+=1;
                    intent.putExtra("fri",1);
                }
                else
                    intent.putExtra("fri",0);
                if(iSat.getVisibility()== View.VISIBLE){
                    noOfWorkingDays+=1;
                    intent.putExtra("sat",1);
                }
                else
                    intent.putExtra("sat",0);
                //Toast.makeText(getApplicationContext(), noOfWorkingDays+"", Toast.LENGTH_LONG).show();

                ContentValues values = new ContentValues();
                values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_MIN_ATTENDANCE, minAttendance);
                values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_NO_OF_WORKING_DAYS, noOfWorkingDays);

                sqLiteDatabase.insert(
                        DatabaseContract.DatabaseEntry.TABLE_NAME_BASIC_DETAILS,
                        null,
                        values);

                /*sqLiteDatabase=databaseBasicDetailsHelper.getReadableDatabase();
                String[] projection = {
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_MIN_ATTENDANCE,
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_NO_OF_WORKING_DAYS

                };
                Cursor cursor=sqLiteDatabase.query(DatabaseContract.DatabaseEntry.TABLE_NAME_BASIC_DETAILS,projection,null,null,null,null,null);
                cursor.moveToFirst();
                int minAtt= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_MIN_ATTENDANCE));
                int WorkingDays=cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_NO_OF_WORKING_DAYS));
                Log.d("aaaaaaaaaaaaaa", minAtt + " " + WorkingDays);
                Toast.makeText(getApplicationContext(), minAtt + " " + WorkingDays, Toast.LENGTH_LONG);*/


                startActivity(intent);
            }
        });

    }
}
