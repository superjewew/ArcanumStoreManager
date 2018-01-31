package com.arcanum.arcanumstoremanager.feature.userslist;

import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.usecase.GetAllUserUseCase;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 31/01/18.
 */

@Module
public class AccountsActivityModule {

    @Provides
    Router provideRouter(AccountsActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    AccountsContract.View provideMainView(AccountsActivity_ activity){
        return activity;
    }

    @Provides
    AccountsContract.Presenter provideMainPresenter(AccountsContract.View mainView, GetAllUserUseCase useCase){
        return new AccountsPresenter(mainView, useCase);
    }
}
