package com.arcanum.arcanumstoremanager.domain.repo;

import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public interface VisitRepository {
    Completable createVisit(String username);
    Single<List<VisitWithName>> getAllVisits();
    Single<List<VisitWithName>> getAllVisitsBetween(Long start, Long end);
}
