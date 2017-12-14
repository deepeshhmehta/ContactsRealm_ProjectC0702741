package com.example.deepeshmehta.contactsrealm;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by deepeshmehta on 2017-12-12.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
