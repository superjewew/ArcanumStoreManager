package com.arcanum.arcanumstoremanager.di;

import android.app.Activity;

import com.arcanum.arcanumstoremanager.feature.RouterImpl;
import com.arcanum.arcanumstoremanager.base.Router;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 24/01/18.
 */

@Module
public abstract class ActivityModule<V extends Activity> {

    @Provides
    Router provideRouter(V activity) {
        return new RouterImpl(activity);
    }
}
