package com.arcanum.arcanumstoremanager.domain.usecase;

import com.arcanum.arcanumstoremanager.base.BaseUseCaseWithParam;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 01/02/18.
 */

public class GetUserByIdUseCase implements BaseUseCaseWithParam<Integer, User> {

    private UserRepository repository;

    @Inject
    public GetUserByIdUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<User> execute(Integer param) {
        return repository.getUserById(param);
    }
}
