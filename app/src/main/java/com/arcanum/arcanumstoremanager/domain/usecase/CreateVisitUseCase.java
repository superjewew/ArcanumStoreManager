package com.arcanum.arcanumstoremanager.domain.usecase;

import com.arcanum.arcanumstoremanager.base.CompletableUseCase;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.VisitRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 29/01/18.
 */

public class CreateVisitUseCase implements CompletableUseCase<String> {

    private VisitRepository repo;

    @Inject
    public CreateVisitUseCase(VisitRepository repo) {
        this.repo = repo;
    }

    @Override
    public Completable execute(String param) {
        return repo.createVisit(param);
    }
}
