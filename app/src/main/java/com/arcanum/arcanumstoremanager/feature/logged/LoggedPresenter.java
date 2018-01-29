package com.arcanum.arcanumstoremanager.feature.logged;

import android.util.Log;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.usecase.FindUserUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.CreateVisitUseCase;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 29/01/18.
 */

public class LoggedPresenter extends BasePresenter<LoggedContract.View> implements LoggedContract.Presenter {

    @Inject
    FindUserUseCase findUserUseCase;

    @Inject
    CreateVisitUseCase createVisitUseCase;

    @Inject
    LoggedPresenter(LoggedContract.View mainView) {
        attachView(mainView);
    }

    @Override
    public void updateVisit(String username) {
        findUserUseCase.execute(username)
                .map(user -> createVisitUseCase.execute(user))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void onSuccess(Completable c) {
        Log.d(getClass().getSimpleName(), "Visit created");
        mView.closeScreen();
        detachView();
    }

    private void onFailed(Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }
}
