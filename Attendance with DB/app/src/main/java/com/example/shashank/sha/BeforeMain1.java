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
import android.widget.EditText;
import android.widget.Toast;

public class BeforeMain1 extends AppCompatActivity {

    private Button main1Next;
    private EditText totHeld,totPresent,minPercentage;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_main1);

        final DatabaseTotClassesHelper totClassesHelper=new DatabaseTotClassesHelper(this);
        sqLiteDatabase=totClassesHelper.getWritableDatabase();

        minPercentage=(EditText) findViewById(R.id.minAttendancePercent);
        totHeld=(EditText) findViewById(R.id.totalClassesHeld);
        totPresent=(EditText) findViewById(R.id.totalClassesPresent);
        main1Next =(Button) findViewById(R.id.nextButton1);
        //Toast.makeText(getApplicationContext(),"cannot be empty",Toast.LENGTH_LONG).show();
        main1Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("aaaaaaaaaaaaaa","123"+totHeld.getText().toString()+"123");
                if(totHeld.getText().toString().matches("")||
                        totPresent.getText().toString().matches("")||
                        minPercentage.getText().toString().matches("")) {
                    //Log.d("zzzzzzzzzzzzzzzzzzz","123"+totHeld.getText().toString()+"123");
                    Toast.makeText(getApplicationContext(), "Text fields cannot be empty", Toast.LENGTH_LONG).show();
                }
                else if(Integer.parseInt(totHeld.getText().toString()) < Integer.parseInt(totPresent.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Total classes cannot be less than present classes", Toast.LENGTH_LONG).show();
                }
                else {
                    ContentValues values = new ContentValues();
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_HELD, Integer.parseInt(totHeld.getText().toString()));
                    values.put(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT, Integer.parseInt(totPresent.getText().toString()));

                    sqLiteDatabase.insert(
                            DatabaseContract.DatabaseEntry.TABLE_NAME_TOT_CLASSES,
                            null,
                            values);

                    Intent intent=new Intent(BeforeMain1.this,BeforeMain2.class);
                    intent.putExtra("minAttendance",Integer.parseInt(minPercentage.getText().toString()));
                    startActivity(intent);
                }
                /*sqLiteDatabase=totClassesHelper.getReadableDatabase();
                String[] projection = {
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_HELD,
                        DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT

                };
                Cursor cursor=sqLiteDatabase.query(DatabaseContract.DatabaseEntry.TABLE_NAME_TOT_CLASSES,projection,null,null,null,null,null);
                cursor.moveToFirst();
                int tot_held= cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_HELD));
                int tot_pre=cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_NAME_TOT_PRESENT));
                Log.d("aaaaaaaaaaaaaa",tot_held+" "+tot_pre);
                Toast.makeText(getApplicationContext(),tot_held + " "+tot_pre,Toast.LENGTH_LONG);*/
            }
        });



    }
}
