package com.arcanum.arcanumstoremanager.feature.dashboard;

import com.arcanum.arcanumstoremanager.domain.usecase.GetVisitsUseCase;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;
import com.arcanum.arcanumstoremanager.base.Router;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 24/01/18.
 */

@Module
public class DashboardActivityModule {
    @Provides
    Router provideRouter(DashboardActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    DashboardContract.View provideView(DashboardActivity_ loginActivity){
        return loginActivity;
    }

    @Provides
    DashboardContract.Presenter providePresenter(DashboardContract.View mainView){
        return new DashboardPresenter(mainView);
    }
}
