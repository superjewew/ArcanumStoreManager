package com.arcanum.arcanumstoremanager.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.arcanum.arcanumstoremanager.data.VisitDao;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;

/**
 * Created by norman on 31/01/18.
 */

@Database(entities = {Visit.class}, version = 1)
public abstract class VisitDatabase extends RoomDatabase {
    public abstract VisitDao visitDao();
}
