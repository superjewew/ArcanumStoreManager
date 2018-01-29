package com.arcanum.arcanumstoremanager.domain.usecase;

import com.arcanum.arcanumstoremanager.base.BaseUseCase;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;
import com.arcanum.arcanumstoremanager.domain.repo.VisitRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public class GetVisitsUseCase implements BaseUseCase<List<Visit>> {

    VisitRepository repository;

    @Inject
    public GetVisitsUseCase(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<Visit>> execute() {
        return repository.getAllVisits();
    }
}
