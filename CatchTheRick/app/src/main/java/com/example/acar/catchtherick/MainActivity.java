package com.example.acar.catchtherick;


/*
SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.acar.catchtherick", Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("HighScore",0).apply();
        int hScore = sharedPreferences.getInt("HighScore",0);
        highScore.setText("High Score: "+hScore);
        if(score>hScore){
        sharedPreferences.edit().putInt("HighScore",score);
        highScore.setText("High Score: "+score);
        }
*/
import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText, timeText, highScore;
    ImageView ImageView1,ImageView2,ImageView3,ImageView4,ImageView5,ImageView6,ImageView7,ImageView8,ImageView9;
    int score;
    Handler handler;
    Runnable runable;

    ImageView [] ImageArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView1 = findViewById(R.id.imageView);
        ImageView2 = findViewById(R.id.imageView2);
        ImageView3 = findViewById(R.id.imageView3);
        ImageView4 = findViewById(R.id.imageView4);
        ImageView5 = findViewById(R.id.imageView5);
        ImageView6 = findViewById(R.id.imageView6);
        ImageView7 = findViewById(R.id.imageView7);
        ImageView8 = findViewById(R.id.imageView8);
        ImageView9 = findViewById(R.id.imageView9);
        highScore = findViewById(R.id.highScoreText);
        ImageArray = new ImageView[]{ImageView1,ImageView2,ImageView3,ImageView4,ImageView5,ImageView6,ImageView7,ImageView8,ImageView9};

        HideImages();
        score=0;


        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText = findViewById(R.id.TimeText);
                timeText.setText("Time: "+millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                timeText = findViewById(R.id.TimeText);
                timeText.setText("Time Off ");
                handler.removeCallbacks(runable);
                for(ImageView image: ImageArray){
                    image.setVisibility(View.INVISIBLE);
                }
            }
        }.start();




    }

    public void increaseScore(View view){
        score++;
        scoreText = findViewById(R.id.ScoreText);
        scoreText.setText("Score: "+score);
    }


    public void HideImages(){
        handler = new Handler();
        runable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image: ImageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random rnd = new Random();
                ImageArray[rnd.nextInt(8-0)].setVisibility(View.VISIBLE);

                handler.postDelayed(this,400);
            }
        };
        handler.post(runable);

    }
}
