package com.arcanum.arcanumstoremanager.feature.userdetail;

import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 01/02/18.
 */

@Module
public class AccountDetailActivityModule {

    @Provides
    Router provideRouter(AccountDetailActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    AccountDetailContract.View provideMainView(AccountDetailActivity_ activity) {
        return activity;
    }

    @Provides
    AccountDetailContract.Presenter provideMainPresenter(AccountDetailContract.View mainView, GetUserUseCase getUseCase) {
        return new AccountDetailPresenter(mainView, getUseCase);
    }
}
