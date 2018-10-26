package com.example.acar.traindb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        textView = (TextView)findViewById(R.id.deneme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            database = this.openOrCreateDatabase("Deneme",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE İF NOT EXİSTS deneme (name VARCHAR , age INT(2))");
            //database.execSQL("Insert into  deneme (name,age) VALUES ('burak',45)");

        }catch (Exception e){
            e.printStackTrace();
        }

        Cursor cursor = database.rawQuery("Select * from deneme",null);
        int nameIx = cursor.getColumnIndex("name");
        int ageIx = cursor.getColumnIndex("name");
        cursor.moveToFirst();

            textView.setText(cursor.getString(nameIx));
            //cursor.moveToNext();

    }
}
