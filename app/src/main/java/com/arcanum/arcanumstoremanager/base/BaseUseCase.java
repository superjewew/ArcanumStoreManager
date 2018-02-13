package com.arcanum.arcanumstoremanager.base;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public interface BaseUseCase<R> {
    Single<R> execute();
}
