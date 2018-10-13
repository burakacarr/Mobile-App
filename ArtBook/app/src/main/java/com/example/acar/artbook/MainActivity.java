package com.example.acar.artbook;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // resim listesini static yaptım diğer activity de de kullanmak için
    static ArrayList<Bitmap> artImage;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater  menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_art,menu);


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add_art){
            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.ListView);

        //array listleri listview e vereceğiz bu yüzden imajar ve yazılar için arraylist oluşturacağız
        final ArrayList<String> artName = new ArrayList<String>();
        artImage = new ArrayList<Bitmap>();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,artName);
        listView.setAdapter(arrayAdapter);

        try{
            Main2Activity.database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
            Main2Activity.database.execSQL("CREATE TABLE IF NOT EXISTS  arts (NAME VARCHAR, IMAGE BLOB)");
            Cursor cursor = Main2Activity.database.rawQuery("Select * from arts ", null);

            int nameIx = cursor.getColumnIndex("NAME");
            int ımageIx = cursor.getColumnIndex("IMAGE");


            cursor.moveToFirst();


            while(cursor!=null){
                // db den ismi çektik
                artName.add(cursor.getString(nameIx));
                // db de byte array olarak kayıtlı resmi çekme
                byte [] bytes = cursor.getBlob(ımageIx);
                Bitmap ımage = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                artImage.add(ımage);
                cursor.moveToNext();
                arrayAdapter.notifyDataSetChanged();

            }
        }catch (Exception e){
            e.printStackTrace();
        };


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ıntent = new Intent(getApplicationContext(), Main2Activity.class);
                ıntent.putExtra("info","old");
                ıntent.putExtra("artName",artName.get(position));
                ıntent.putExtra("position",position);
                startActivity(ıntent);
        //chosenImage diye static bir bitmap değişkeni açarak bu şekilde aktarabiliriz         chosenImage= artImage.get(position);
            }
        });

    }
}
