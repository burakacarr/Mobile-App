package com.example.acar.landmarkbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static Bitmap selectedImages;
    static int DetailNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView)findViewById(R.id.ListView);

        final ArrayList<String> LandmarkNames = new ArrayList<String>();

        LandmarkNames.add("Pissa");
        LandmarkNames.add("Colosseum");
        LandmarkNames.add("Eiffel");
        LandmarkNames.add("London Bridge");

        // resimleri bitmap şeklinde ekleyebiliyoruz.
        final ArrayList<Bitmap> LandmarkImages = new ArrayList<Bitmap>();

        // bitmap oluşturuyoruz resimleri listeye atmak için
        Bitmap pissa = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.pisa);
        // tüm resimler için aynı işlemi yapıyoruz
        Bitmap Colosseum = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.coliseum);
        Bitmap Eiffel = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.eiffel);
        Bitmap LondonBridge = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.london);



        // şimdi arraylistime bitmapleri ekliyorum
        LandmarkImages.add(pissa);
        LandmarkImages.add(Colosseum);
        LandmarkImages.add(Eiffel);
        LandmarkImages.add(LondonBridge);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,LandmarkNames);
        listView.setAdapter(arrayAdapter);


        // hangi item değerine tıkladığımızı bulup ona göre yönlendirme yapacağız
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("name",LandmarkNames.get(position));
               //static değişken ile kullanma
               // selectedImages = LandmarkImages.get(position);
                DetailNumber=position;
                //class yardımıyla kullanma
                Bitmap bitmap = LandmarkImages.get(position);

                Globals globals = Globals.getInstance();
                globals.setData(bitmap);
                startActivity(intent);
            }
        });
    }
}
