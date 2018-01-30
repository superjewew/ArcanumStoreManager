package com.arcanum.arcanumstoremanager.data;

import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;
import com.arcanum.arcanumstoremanager.domain.repo.VisitRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public class VisitRepoImpl implements VisitRepository {

    @Override
    public Completable createVisit(User user) {
        return Completable.defer(() -> innerCreateVisit(user));
    }

    @Override
    public Single<List<Visit>> getAllVisits() {
        return Single.defer(() -> Single.just(innerGetVisits()));
    }

    private Completable innerCreateVisit(User user) {
        return Completable.fromAction(() -> {
            Visit visit = new Visit();
            visit.setVisitTime(Calendar.getInstance().getTime());
            visit.setVisitor(user.getUsername());
        });
    }

    private List<Visit> innerGetVisits() {
        List<Visit> visits = new ArrayList<>();
        Visit visit = new Visit();
        visit.setVisitTime(Calendar.getInstance().getTime());
        visit.setVisitor("Full Name");
        visits.add(visit);
        return visits;
    }

}
