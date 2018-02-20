package com.arcanum.arcanumstoremanager.data;

import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;
import com.arcanum.arcanumstoremanager.data.database.ArcanumDatabase;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;
import com.arcanum.arcanumstoremanager.domain.repo.VisitRepository;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public class VisitRepoImpl implements VisitRepository {

    ArcanumDatabase db;
    DatabaseReference visitRemoteDb;

    @Inject
    public VisitRepoImpl(ArcanumDatabase db, DatabaseReference remoteDb) {
        this.db = db;
        visitRemoteDb = remoteDb;
    }

    @Override
    public Completable createVisit(User user) {
        return Completable.defer(() -> innerCreateVisit(user));
    }

    @Override
    public Single<List<VisitWithName>> getAllVisits() {
        return RxFirebaseDatabase
                .observeSingleValueEvent(visitRemoteDb.child("visits"), DataSnapshotMapper.listOf(VisitWithName.class))
                .toSingle();
    }

    @Override
    public Single<List<VisitWithName>> getAllVisitsBetween(Long start, Long end) {
        return Single.defer(() -> db.visitDao().getAllVisitBetweenTime(start, end));
    }

    private Completable innerCreateVisit(User user) {
        VisitWithName visit = new VisitWithName();
        visit.username = user.getUsername();
        visit.fullname = user.getFullname();
        visit.visittime = Calendar.getInstance().getTimeInMillis();
        return RxFirebaseDatabase.setValue(visitRemoteDb.child("visits").push(), visit);
    }

}
