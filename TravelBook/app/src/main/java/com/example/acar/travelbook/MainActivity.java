package com.example.acar.travelbook;

import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> names = new ArrayList<String>();
    static ArrayList<LatLng> locations = new ArrayList<LatLng>();
    static ArrayAdapter arrayAdapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_place,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add_place){
            //diğer sayfaya gideceğiz
            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);

        try{
            MapsActivity.database = this.openOrCreateDatabase("Places",MODE_PRIVATE,null);
            Cursor cursor = MapsActivity.database.rawQuery("Select * from places ",null);

            int nameIx = cursor.getColumnIndex("name");
            int latIx = cursor.getColumnIndex("lat");
            int longIx = cursor.getColumnIndex("long");

            //cursor.moveToFirst();
            while (cursor.moveToNext()){
                String nameFromDb = cursor.getString(nameIx);
                String latFromDb = cursor.getColumnName(latIx);
                String longFromDb = cursor.getColumnName(longIx);

                names.add(nameFromDb);

                Double l1 = Double.parseDouble(latFromDb);
                Double l2 = Double.parseDouble(longFromDb);
                LatLng latLng = new LatLng(l1,l2);
                locations.add(latLng);
            }
            cursor.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ıntent = new Intent(getApplicationContext(),MapsActivity.class);
                ıntent.putExtra("position",position);
                ıntent.putExtra("info","old");
                startActivity(ıntent);
            }
        });

    }

}
