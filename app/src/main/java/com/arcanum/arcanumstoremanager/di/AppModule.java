package com.arcanum.arcanumstoremanager.di;

import android.app.Application;
import android.content.Context;

import com.arcanum.arcanumstoremanager.base.BaseUseCaseWithParam;
import com.arcanum.arcanumstoremanager.base.CompletableUseCase;
import com.arcanum.arcanumstoremanager.data.UserRepoImpl;
import com.arcanum.arcanumstoremanager.data.VisitRepoImpl;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;
import com.arcanum.arcanumstoremanager.domain.repo.VisitRepository;
import com.arcanum.arcanumstoremanager.domain.usecase.CreateVisitUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.FindUserUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.GetVisitsUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.RegisterUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 24/01/18.
 */

@Module
public abstract class AppModule {

    @Provides
    @Singleton
    static Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    static UserRepository provideRepository() {
        return new UserRepoImpl();
    }

    @Provides
    @Singleton
    static VisitRepository provideVisitRepository() {
        return new VisitRepoImpl();
    }

    @Provides
    @Singleton
    static CompletableUseCase<User> provideRegisterUseCase(UserRepository repository) {
        return new RegisterUseCase(repository);
    }

    @Provides
    @Singleton
    static BaseUseCaseWithParam<String, User> provideLoginUseCase(UserRepository repository) {
        return new FindUserUseCase(repository);
    }

    @Provides
    @Singleton
    static CreateVisitUseCase provideUpdateUseCase(VisitRepository repository) {
        return new CreateVisitUseCase(repository);
    }

    @Provides
    @Singleton
    static GetVisitsUseCase provideGetVisitUseCase(VisitRepository repository) {
        return new GetVisitsUseCase(repository);
    }
}
