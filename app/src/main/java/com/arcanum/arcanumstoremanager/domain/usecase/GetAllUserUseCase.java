package com.arcanum.arcanumstoremanager.domain.usecase;

import com.arcanum.arcanumstoremanager.base.BaseUseCase;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 31/01/18.
 */

public class GetAllUserUseCase implements BaseUseCase<List<User>> {

    private UserRepository repository;

    @Inject
    public GetAllUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<User>> execute() {
        return repository.getAllUser();
    }
}
