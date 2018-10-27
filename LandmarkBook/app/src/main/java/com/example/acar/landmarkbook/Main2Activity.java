package com.example.acar.landmarkbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    TextView textView, tvDetail;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ArrayList<String> LandmarkDetail = new ArrayList<String>();
        Intent intent = getIntent();

        LandmarkDetail.add("The leaning of the Tower of Pisa comes into the story in 1173, when construction began. Thanks to the soft ground, it had begun to lean by the time its builders got to the third story, in 1178. Shifting soil had destabilized the tower's foundations.");
        LandmarkDetail.add("The capital of Italy Colosseum in Rome, also known as Flavian Amphitheater is an arena. Masters by a commander Vespasian began its construction in the year 72 AD and was completed in the period of Titus in the year 80 AD. Subsequent changes have been made at the time of Domitian reign. Roman emperors drank here to entertain the people");
        LandmarkDetail.add("The most popular tourist place in Paris has stretched to the Parisian skies for 127 years. Although now symbolic of France, it wasn’t meant to last. Without a doubt, the turning point in the Eiffel Tower history took place at the 1889 Universal Exposition. In commemoration of the 100th anniversary of the French Revolution, a competition was organized with the aim to “build on the Champ-de-Mars an iron tower with a square base, 125 meters wide and 300 meters high.” Out of the 107 proposals submitted, Gustave Eiffel’s was chosen. By his side were engineers Maurice Koechlin and Emile Nouguier as well as architect Stephen Sauvestre.");
        LandmarkDetail.add("There has been a bridge across the River Thames in London for nearly 2,000 years. The first \"London Bridge\" was built by the Romans in 43 A.D. They built a temporary pontoon bridge which was planks laid across a row of anchored boats, or they may have used ferry boats. ");
        String name = intent.getStringExtra("name");
        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView.setText(name);

        //static ile çekme
        //imageView.setImageBitmap(MainActivity.selectedImages);
        tvDetail = (TextView)findViewById(R.id.tvDetail);
        tvDetail.setText(LandmarkDetail.get(MainActivity.DetailNumber));
       // instance ile resmi çekme
       Globals globals = Globals.getInstance();
       Bitmap bitmap = globals.getData();
       imageView.setImageBitmap(bitmap);

    }
}
