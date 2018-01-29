package com.arcanum.arcanumstoremanager;

import com.arcanum.arcanumstoremanager.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by norman on 24/01/18.
 */

public class ArcanumApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
