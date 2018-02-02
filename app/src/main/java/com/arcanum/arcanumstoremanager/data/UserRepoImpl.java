package com.arcanum.arcanumstoremanager.data;

import com.arcanum.arcanumstoremanager.data.database.ArcanumDatabase;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 25/01/18.
 */

public class UserRepoImpl implements UserRepository {

    ArcanumDatabase db;

    @Inject
    public UserRepoImpl(ArcanumDatabase db) {
        this.db = db;
    }

    @Override
    public Completable createUser(User user) {
        return Completable.defer(() -> Completable.fromAction(() -> innerCreateUser(user)));
    }

    @Override
    public Single<List<User>> getAllUser() {
        return Single.defer(this::innerGetAllUsers);
    }

    @Override
    public Single<User> getUserByUsername(String username) {
        return Single.defer(() -> innerGetUserByUsername(username));
    }

    @Override
    public Single<User> getUserById(int id) {
        return Single.defer(() -> innerGetUserById(id));
    }

    @Override
    public Completable updateUser(User user) {
        return Completable.defer(() -> innerUpdateUser(user));
    }

    @Override
    public void deleteUser(int id) {

    }
    
    private Completable innerUpdateUser(User user) {
        return Completable.fromAction(() -> db.userDao().updateUsers(user));
    }

    private long innerCreateUser(User user) {
        return db.userDao().insertUser(user);
    }

    private Single<User> innerGetUserByUsername(String username) {
        return db.userDao().getUserByUsername(username);
    }

    private Single<User> innerGetUserById(int id) {
        return db.userDao().getUserById(id);
    }

    private Single<List<User>> innerGetAllUsers() {
        return db.userDao().loadAllUsers();
    }

}
