package com.arcanum.arcanumstoremanager;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.arcanum.arcanumstoremanager.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by norman on 24/01/18.
 */

public class ArcanumApp extends DaggerApplication {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
