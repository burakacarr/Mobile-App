package com.example.acar.parselearning;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);


        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("O96t5gTY9b7mLzPZVxvIvIyMyfhAIH7VlLnBSN9q")
        .clientKey("eaR866FdQeVQ1DPy4DZBa4JXcNWPD81amyJ5ojSY")
        .server("https://parseapi.back4app.com/")
        .build()
        );



    }
}
