package com.arcanum.arcanumstoremanager.feature.logged;

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

    private FindUserUseCase findUserUseCase;

    private CreateVisitUseCase createVisitUseCase;

    @Inject
    public LoggedPresenter(LoggedContract.View mainView, FindUserUseCase findUserUseCase, CreateVisitUseCase createVisitUseCase) {
        attachView(mainView);
        this.findUserUseCase = findUserUseCase;
        this.createVisitUseCase = createVisitUseCase;
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
        mView.closeScreen();
    }

    private void onFailed(Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }
}
