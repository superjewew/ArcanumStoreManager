package com.arcanum.arcanumstoremanager.domain.repo;

import com.arcanum.arcanumstoremanager.domain.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 24/01/18.
 */

public interface UserRepository {
    Completable createUser(User user);
    Single<List<User>> getAllUser();
    Single<User> getUserByUsername(String username);
    Completable updateUser(User user);
    void deleteUser(int id);
}
