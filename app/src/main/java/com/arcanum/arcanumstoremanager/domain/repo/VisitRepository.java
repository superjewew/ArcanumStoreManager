package com.arcanum.arcanumstoremanager.domain.repo;

import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;
import com.arcanum.arcanumstoremanager.domain.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public interface VisitRepository {
    Completable createVisit(User user);
    Single<List<VisitWithName>> getAllVisits();
    Single<List<VisitWithName>> getAllVisitsBetween(Long start, Long end);
}
