package com.arcanum.arcanumstoremanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.arcanum.arcanumstoremanager.domain.entity.Visit;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by norman on 31/01/18.
 */

@Dao
public interface VisitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertVisit(Visit visit);

    @Query("SELECT visit.visitor AS username, user.fullname AS fullname, visit.visitTime AS visittime "
            + "FROM visit, user "
            + "WHERE visit.visitor = user.username")
    public Single<List<VisitWithName>> getAllVisit();

    static class VisitWithName {
        public String username;
        public String fullname;
        public Long visittime;
    }
}
