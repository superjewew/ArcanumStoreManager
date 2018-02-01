package com.arcanum.arcanumstoremanager.feature.useredit;

import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.UpdateUserUseCase;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 01/02/18.
 */

@Module
public class AccountEditActivityModule {
    @Provides
    Router provideRouter(AccountEditActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    AccountEditContract.View provideMainView(AccountEditActivity_ activity) {
        return activity;
    }

    @Provides
    AccountEditContract.Presenter provideMainPresenter(AccountEditContract.View mainView, GetUserUseCase getUseCase, UpdateUserUseCase updateUserUseCase) {
        return new AccountEditPresenter(mainView, getUseCase, updateUserUseCase);
    }
}
