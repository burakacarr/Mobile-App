package com.example.acar.catchtherick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    Intent intent;
    TextView PlayText;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        PlayText = (TextView)findViewById(R.id.textPlay);
        PlayText.setVisibility(View.INVISIBLE);
        counter=0;
    }


    public void Start(View view){
        intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void Play(View view){
        if(counter==0){
            PlayText.setVisibility(View.VISIBLE);
            counter=1;
        }
        else{
            counter=0;
            PlayText.setVisibility(View.INVISIBLE);
        }
    }
}
