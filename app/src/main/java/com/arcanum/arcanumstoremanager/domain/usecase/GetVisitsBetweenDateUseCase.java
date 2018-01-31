package com.arcanum.arcanumstoremanager.domain.usecase;

import com.arcanum.arcanumstoremanager.base.BaseUseCaseWithMultipleParams;
import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;
import com.arcanum.arcanumstoremanager.domain.repo.VisitRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 31/01/18.
 */

public class GetVisitsBetweenDateUseCase implements BaseUseCaseWithMultipleParams<Long,List<VisitWithName>> {

    private VisitRepository repository;

    @Inject
    public GetVisitsBetweenDateUseCase(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<VisitWithName>> execute(Long ... param) {
        return repository.getAllVisitsBetween(param[0], param[1]);
    }
}
