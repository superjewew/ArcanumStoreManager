package com.arcanum.arcanumstoremanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.arcanum.arcanumstoremanager.domain.entity.User;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by norman on 30/01/18.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertUser(User user);

    @Update
    public void updateUsers(User...users);

    @Delete
    public void deleteUsers(User...users);

    @Query("SElECT * FROM user")
    public Single<List<User>> loadAllUsers();

    @Query("SELECT * FROM user WHERE username = :username")
    public Single<User> getUserByUsername(String username);
}