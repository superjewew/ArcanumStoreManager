package com.arcanum.arcanumstoremanager.domain.usecase;

import com.arcanum.arcanumstoremanager.base.CompletableUseCase;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 01/02/18.
 */

public class UpdateUserUseCase implements CompletableUseCase<User> {

    private UserRepository repository;

    @Inject
    public UpdateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable execute(User param) {
        return repository.updateUser(param);
    }
}
