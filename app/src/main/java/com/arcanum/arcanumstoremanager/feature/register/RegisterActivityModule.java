package com.arcanum.arcanumstoremanager.feature.register;

import com.arcanum.arcanumstoremanager.base.CompletableUseCase;
import com.arcanum.arcanumstoremanager.data.UserRepoImpl;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;
import com.arcanum.arcanumstoremanager.domain.usecase.RegisterUseCase;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;
import com.arcanum.arcanumstoremanager.base.Router;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 24/01/18.
 */

@Module
public class RegisterActivityModule {

    @Provides
    Router provideRouter(RegisterActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    RegisterContract.View provideMainView(RegisterActivity_ loginActivity){
        return loginActivity;
    }

    @Provides
    RegisterContract.Presenter provideMainPresenter(RegisterContract.View mainView, RegisterUseCase useCase){
        return new RegisterPresenter(mainView, useCase);
    }
}
