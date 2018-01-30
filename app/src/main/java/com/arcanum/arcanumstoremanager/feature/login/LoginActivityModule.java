package com.arcanum.arcanumstoremanager.feature.login;

import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;
import com.arcanum.arcanumstoremanager.base.Router;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 24/01/18.
 */

@Module
public class LoginActivityModule {

    @Provides
    Router provideRouter(LoginActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    LoginContract.View provideMainView(LoginActivity_ loginActivity){
        return loginActivity;
    }

    @Provides
    LoginContract.Presenter provideMainPresenter(LoginContract.View mainView, GetUserUseCase useCase){
        return new LoginPresenter(mainView, useCase);
    }
}
