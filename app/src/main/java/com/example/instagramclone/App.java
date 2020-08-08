package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nb227uhtq6Plm8qglG0brFtkbR91cQJbIPT8P5T4")
                // if defined
                .clientKey("lBO4LbqlwOUK8fdZsrQmq1zorVXgsyqSIV5Ptc5N")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
