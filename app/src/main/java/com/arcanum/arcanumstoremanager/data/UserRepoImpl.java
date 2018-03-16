package com.arcanum.arcanumstoremanager.data;

import com.arcanum.arcanumstoremanager.data.database.ArcanumDatabase;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import javax.inject.Inject;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseAuth;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 25/01/18.
 */

public class UserRepoImpl implements UserRepository {

    ArcanumDatabase db;
    DatabaseReference userRemoteDb;

    @Inject
    public UserRepoImpl(ArcanumDatabase db, DatabaseReference remoteDb) {
        this.db = db;
        this.userRemoteDb = remoteDb;
    }

    @Override
    public Completable createUser(User user) {
        return Completable.defer(() ->  innerCreateUser(user));
    }

    @Override
    public Single<List<User>> getAllUser() {
        return RxFirebaseDatabase
                .observeSingleValueEvent(userRemoteDb.child("users"), DataSnapshotMapper.listOf(User.class))
                .toSingle();
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

    public Single<List<String>> getAllId() {
        return RxFirebaseDatabase
                .observeSingleValueEvent(userRemoteDb.child("users").child("id"), DataSnapshotMapper.listOf(String.class))
                .toSingle();
    }
    
    private Completable innerUpdateUser(User user) {
        return innerCreateUser(user);
    }

    private Single<User> innerGetUserByUsername(String username) {
        return RxFirebaseDatabase
                .observeSingleValueEvent(userRemoteDb.child("users").child(username), User.class)
                .toSingle();
    }

    private Single<User> innerGetUserById(int id) {
        return RxFirebaseDatabase
                .observeSingleValueEvent(userRemoteDb.child("users").orderByChild("" + id), User.class)
                .toSingle();
    }

    private Single<List<User>> innerGetAllUsers() {
        return db.userDao().loadAllUsers();
    }

    private Completable innerCreateUser(User user) {
        return RxFirebaseDatabase
                .setValue(userRemoteDb.child("users").child(user.getUsername()), user);
    }

}
