package com.arcanum.arcanumstoremanager.domain.usecase;

import com.arcanum.arcanumstoremanager.base.BaseUseCaseWithParam;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public class GetUserUseCase implements BaseUseCaseWithParam<String, User> {

    private UserRepository repository;

    @Inject
    public GetUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<User> execute(String param) {
        return repository.getUserByEmail(param);
    }
}
