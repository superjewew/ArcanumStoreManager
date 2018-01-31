package com.arcanum.arcanumstoremanager.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.arcanum.arcanumstoremanager.data.UserDao;
import com.arcanum.arcanumstoremanager.data.VisitDao;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;

/**
 * Created by norman on 30/01/18.
 */

@Database(entities = {User.class, Visit.class}, version = 1)
public abstract class ArcanumDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract VisitDao visitDao();
}
