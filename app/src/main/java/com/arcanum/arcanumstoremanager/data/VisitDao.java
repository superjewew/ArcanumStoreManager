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

    @Query("SELECT * FROM visit")
    public Single<List<Visit>> getAllVisit();
}
