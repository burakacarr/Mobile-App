package com.example.acar.catchtherick;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    TextView HscoreText;
    TextView levelText;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    int score;
    int level=0;
    SharedPreferences sharedPreferences;
    ImageView [] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.acar.catchtherick",Context.MODE_PRIVATE);
        //sharedPreferences.edit().putInt("Hscore",0).apply();
        HscoreText = (TextView)findViewById(R.id.textHighScore);
        HscoreText.setText("High Score: "+sharedPreferences.getInt("Hscore",0));
        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        hideImages();

        score = 0;



        final long milisn = 30000;

        new CountDownTimer(milisn,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeText = (TextView) findViewById(R.id.textTime);
                timeText.setText("Time: " + millisUntilFinished / 1000);
                if(score>(level*30+15)){
                    millisUntilFinished = millisUntilFinished+15000;
                    level++;
                    levelText = (TextView)findViewById(R.id.textLevel);
                    levelText.setText("Level: "+level);
                }
            }

            @Override
            public void onFinish() {

                timeText = (TextView) findViewById(R.id.textTime);
                timeText.setText("Time's Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                if(score>sharedPreferences.getInt("Hscore",0)){
                    sharedPreferences.edit().putInt("Hscore",score).apply();
                }
                HscoreText = (TextView)findViewById(R.id.textHighScore);
                HscoreText.setText("High Score: "+sharedPreferences.getInt("Hscore",0));

            }
        }.start();

    }



    public void increaseScore(View view) {

        scoreText = (TextView) findViewById(R.id.textScore);

        score++;

        scoreText.setText("Score: " + score);


    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random r = new Random();
                int i = r.nextInt(8-0);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);

            }
        };

        handler.post(runnable);

    }


}
