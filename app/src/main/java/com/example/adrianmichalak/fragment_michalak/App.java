package com.example.adrianmichalak.fragment_michalak;

import android.app.Application;

import com.example.adrianmichalak.fragment_michalak.retrofit.Rest;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Rest.init();
        FlowManager.init(new FlowConfig.Builder(this).build());

    }
}
