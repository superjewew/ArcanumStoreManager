package com.arcanum.arcanumstoremanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

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
            + "WHERE visit.visitor = user.username "
            + "ORDER BY visit.visitTime DESC")
    public Single<List<VisitWithName>> getAllVisit();

    @Query("SELECT visit.visitor AS username, user.fullname AS fullname, visit.visitTime AS visittime "
            + "FROM visit, user "
            + "WHERE visit.visitor = user.username "
            + "AND visit.visitTime BETWEEN :start AND :end "
            + "ORDER BY visit.visitTime DESC")
    public Single<List<VisitWithName>> getAllVisitBetweenTime(Long start, Long end);

    static class VisitWithName implements Comparable<VisitWithName> {
        public String username;
        public String fullname;
        public Long visittime;

        @Override
        public int compareTo(@NonNull VisitWithName o) {
            if(visittime > o.visittime) {
                return -1;
            } else if(visittime < o.visittime) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
