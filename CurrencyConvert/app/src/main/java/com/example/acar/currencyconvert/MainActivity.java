package com.example.acar.currencyconvert;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView CAD,CEF,USD,JPY,TR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CAD = (TextView)findViewById(R.id.CADtext);
        CEF =(TextView)findViewById(R.id.CEFtext);
        USD =(TextView)findViewById(R.id.USDtext);
        JPY =(TextView)findViewById(R.id.JPYtext);
        TR = (TextView)findViewById(R.id.TRtext);
    }

    public void GetRates(){

        DownloadData downloadData = new DownloadData();

        try{
            String url = "http://data.fixer.io/api/latest?access_key=af055eb06fed346bc926322bdc5162bd&format=1";
            downloadData.execute(url);


        }catch (Exception e ){


        }
    }
    private class DownloadData extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            String result="";
            URL url;
            HttpURLConnection httpURLConnection;

            try{

                url = new URL(strings[0]);
                httpURLConnection  = (HttpURLConnection) url.openConnection();
                InputStream ınputStream =  httpURLConnection.getInputStream();
                InputStreamReader ınputStreamReader = new InputStreamReader(ınputStream);

                int data = ınputStreamReader.read();
                //hece hece okuyarak verileri aldık
                while (data>0){

                    char character = (char) data;
                    result += character;

                    data = ınputStreamReader.read();



                }


            }catch (Exception e){
                e.printStackTrace();
                return null;

            }
            return  result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try{

                JSONObject jsonObject = new JSONObject(s);

                JSONObject rates = new JSONObject(jsonObject.getString("rates"));
                TR.setText(rates.getString("TRY"));


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
