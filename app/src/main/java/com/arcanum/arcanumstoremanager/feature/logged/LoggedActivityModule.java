package com.arcanum.arcanumstoremanager.feature.logged;

import com.arcanum.arcanumstoremanager.domain.usecase.CreateVisitUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.FindUserUseCase;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;
import com.arcanum.arcanumstoremanager.base.Router;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 24/01/18.
 */

@Module
public class LoggedActivityModule {

    @Provides
    Router provideRouter(LoggedActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    LoggedContract.View provideView(LoggedActivity_ activity){
        return activity;
    }

    @Provides
    LoggedContract.Presenter providePresenter(LoggedContract.View mainView, FindUserUseCase findUserUseCase, CreateVisitUseCase createVisitUseCase){
        return new LoggedPresenter(mainView, findUserUseCase, createVisitUseCase);
    }
}
