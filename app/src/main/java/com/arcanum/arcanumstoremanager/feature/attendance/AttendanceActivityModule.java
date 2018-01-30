package com.arcanum.arcanumstoremanager.feature.attendance;

import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 29/01/18.
 */

@Module
public class AttendanceActivityModule {

    @Provides
    Router provideRouter(AttendanceActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    AttendanceContract.View provideMainView(AttendanceActivity_ activity){
        return activity;
    }

    @Provides
    AttendanceContract.Presenter providePresenter(AttendanceContract.View mainView){
        return new AttendancePresenter(mainView);
    }
}
