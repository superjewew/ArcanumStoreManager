package com.arcanum.arcanumstoremanager.feature.home;

import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 05/04/18.
 */

@Module
public class HomeActivityModule {

    @Provides
    Router provideRouter(HomeActivity_ activity) {
        return new RouterImpl(activity);
    }
}
