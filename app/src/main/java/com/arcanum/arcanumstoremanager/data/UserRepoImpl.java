package com.arcanum.arcanumstoremanager.data;

import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.repo.UserRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 25/01/18.
 */

public class UserRepoImpl implements UserRepository {

    @Override
    public Completable createUser(User user) {
        return Completable.defer(() -> Completable.fromAction(this::dummyUser));
    }

    @Override
    public Single<List<User>> getAllUser() {
        return null;
    }

    @Override
    public Single<User> getUserByEmail(String email) {
        return Single.defer(() -> Single.just(dummyUserFromEmail(email)));
    }

    @Override
    public Completable updateUser(User user) {
        return Completable.defer(() -> updateUserInner(user).subscribeOn(Schedulers.io()));
    }

    @Override
    public void deleteUser(int id) {

    }
    
    private Completable updateUserInner(User user) {
        return Completable.fromAction(() ->
            {

            }
        );
    }

    private User dummyUser() {
        return new User();
    }

    private User dummyUserFromEmail(String name) {
        User user = dummyUser();
        user.setName(name);
        return user;
    }
}
