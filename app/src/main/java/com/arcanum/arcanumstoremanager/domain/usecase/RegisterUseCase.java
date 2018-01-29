package com.arcanum.arcanumstoremanager.domain.usecase;

import com.arcanum.arcanumstoremanager.base.CompletableUseCase;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 24/01/18.
 */

public class RegisterUseCase implements CompletableUseCase<User> {

    private UserRepository mRepo;

    @Inject
    public RegisterUseCase(UserRepository repository) {
        mRepo = repository;
    }

    @Override
    public Completable execute(User param) {
        return mRepo.createUser(param);
    }
}
