package com.arcanum.arcanumstoremanager.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.arcanum.arcanumstoremanager.base.BaseUseCaseWithParam;
import com.arcanum.arcanumstoremanager.base.CompletableUseCase;
import com.arcanum.arcanumstoremanager.data.UserRepoImpl;
import com.arcanum.arcanumstoremanager.data.VisitRepoImpl;
import com.arcanum.arcanumstoremanager.data.database.ArcanumDatabase;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;
import com.arcanum.arcanumstoremanager.domain.repo.VisitRepository;
import com.arcanum.arcanumstoremanager.domain.usecase.CreateVisitUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserByIdUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.GetVisitsBetweenDateUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.GetVisitsUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.RegisterUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.DaggerApplication;

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
    static ArcanumDatabase provideUserDatabase(DaggerApplication application) {
        return Room.databaseBuilder(application, ArcanumDatabase.class, "users").build();
    }

    @Provides
    @Singleton
    static UserRepository provideRepository(ArcanumDatabase db) {
        return new UserRepoImpl(db);
    }

    @Provides
    @Singleton
    static VisitRepository provideVisitRepository(ArcanumDatabase db) {
        return new VisitRepoImpl(db);
    }

    @Provides
    @Singleton
    static CompletableUseCase<User> provideRegisterUseCase(UserRepository repository) {
        return new RegisterUseCase(repository);
    }

    @Provides
    @Singleton
    static BaseUseCaseWithParam<String, User> provideLoginUseCase(UserRepository repository) {
        return new GetUserUseCase(repository);
    }

    @Provides
    @Singleton
    static BaseUseCaseWithParam<Integer, User> provideGetUserByIdUseCase(UserRepository repository) {
        return new GetUserByIdUseCase(repository);
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

    @Provides
    @Singleton
    static GetVisitsBetweenDateUseCase provideGetVisitBetweenDatesUseCase(VisitRepository repository) {
        return new GetVisitsBetweenDateUseCase(repository);
    }
}
