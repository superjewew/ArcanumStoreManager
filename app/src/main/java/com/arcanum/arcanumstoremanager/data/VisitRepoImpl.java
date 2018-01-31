package com.arcanum.arcanumstoremanager.data;

import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;
import com.arcanum.arcanumstoremanager.data.database.ArcanumDatabase;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;
import com.arcanum.arcanumstoremanager.domain.repo.VisitRepository;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public class VisitRepoImpl implements VisitRepository {

    ArcanumDatabase db;

    @Inject
    public VisitRepoImpl(ArcanumDatabase db) {
        this.db = db;
    }

    @Override
    public Completable createVisit(String username) {
        return Completable.defer(() -> Completable.fromAction(() -> innerCreateVisit(username)));
    }

    @Override
    public Single<List<VisitWithName>> getAllVisits() {
        return Single.defer(() -> db.visitDao().getAllVisit());
    }

    @Override
    public Single<List<VisitWithName>> getAllVisitsBetween(Long start, Long end) {
        return Single.defer(() -> db.visitDao().getAllVisitBetweenTime(start, end));
    }

    private long innerCreateVisit(String username) {
        Visit visit = new Visit();
        visit.setVisitor(username);
        visit.setVisitTime(Calendar.getInstance().getTimeInMillis());
        return db.visitDao().insertVisit(visit);
    }

}
