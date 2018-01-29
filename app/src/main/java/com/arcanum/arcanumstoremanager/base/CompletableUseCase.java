package com.arcanum.arcanumstoremanager.base;

import io.reactivex.Completable;

/**
 * Created by norman on 25/01/18.
 */

public interface CompletableUseCase<P> {
    Completable execute(P param);
}
