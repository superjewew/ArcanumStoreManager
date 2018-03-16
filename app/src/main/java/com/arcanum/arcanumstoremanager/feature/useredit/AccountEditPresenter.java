package com.arcanum.arcanumstoremanager.feature.useredit;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.UpdateUserUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 01/02/18.
 */

public class AccountEditPresenter extends BasePresenter<AccountEditContract.View> implements AccountEditContract.Presenter {

    private GetUserUseCase getUserUseCase;
    private UpdateUserUseCase updateUserUseCase;

    @Inject
    public AccountEditPresenter(AccountEditContract.View view, GetUserUseCase getUserUseCase, UpdateUserUseCase updateUserUseCase) {
        attachView(view);
        this.getUserUseCase = getUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @Override
    public void loadUser(String username) {
        getUserUseCase.execute(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccessGetUser, this::onFailed);
    }

    private void onFailed(Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }

    private void onSuccessGetUser(User user) {
        mView.initViewContent(user);
    }

    @Override
    public void updateUser(User user) {
        updateUserUseCase.execute(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccessUpdateUser, this::onFailed);
    }

    private void onSuccessUpdateUser() {
        mView.closeScreen();
    }
}
