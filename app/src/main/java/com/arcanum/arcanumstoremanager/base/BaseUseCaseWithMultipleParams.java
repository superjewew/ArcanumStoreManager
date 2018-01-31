package com.arcanum.arcanumstoremanager.base;

import io.reactivex.Single;

/**
 * Created by norman on 31/01/18.
 */

public interface BaseUseCaseWithMultipleParams<P, R> {
    Single<R> execute(P ... param);
}
