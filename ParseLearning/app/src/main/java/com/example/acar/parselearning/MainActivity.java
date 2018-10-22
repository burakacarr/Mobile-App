package com.example.acar.parselearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* veri kaydetme
        ParseObject object = new ParseObject("Fruits");

        object.put("name","aplple");
        object.put("calories",100);

        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Object Saved",Toast.LENGTH_LONG).show();
                }
            }
        });
        */

        //kaydettiğimiz verileri çekeceğiz
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Fruits");
        query.getInBackground("iBrSTugYbk", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }
                else{
                    String objectName = object.getString("name");
                    int objectCal = object.getInt("calories");
                    Toast.makeText(getApplicationContext(),objectName,Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
