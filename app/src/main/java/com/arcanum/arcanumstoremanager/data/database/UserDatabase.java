package com.arcanum.arcanumstoremanager.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.arcanum.arcanumstoremanager.data.UserDao;
import com.arcanum.arcanumstoremanager.domain.entity.User;

/**
 * Created by norman on 30/01/18.
 */

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
