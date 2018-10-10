package com.example.acar.landmarkbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView.setText(name);

        //static ile çekme
        //imageView.setImageBitmap(MainActivity.selectedImages);

       // instance ile resmi çekme
       Globals globals = Globals.getInstance();
       Bitmap bitmap = globals.getData();
       imageView.setImageBitmap(bitmap);

    }
}
