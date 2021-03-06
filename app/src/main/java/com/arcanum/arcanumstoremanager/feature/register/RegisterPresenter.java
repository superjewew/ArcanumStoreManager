package com.arcanum.arcanumstoremanager.feature.register;

import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.usecase.RegisterUseCase;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 24/01/18.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    RegisterUseCase registerUseCase;

    @Inject
    RegisterPresenter(RegisterContract.View view, RegisterUseCase registerUseCase) {
        attachView(view);
        this.registerUseCase = registerUseCase;
    }

    @Override
    public void registerUser(User user) {
        if(isValid(user)) {
            registerUseCase.execute(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccess, this::onError);
        }
    }

    private void onSuccess() {
        mView.showLoggedScreen();
    }

    private void onError(final Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }

    private boolean isValid(User user) {
        boolean valid = true;
        clearError();

        if(user.getUsername().equalsIgnoreCase("")) {
            mView.showFormUsernameError("Username can't be empty");
            valid = false;
        }
        if(user.getPassword().equals("")) {
            mView.showFormPasswordError("Password can't be empty");
            valid = false;
        }
        if(user.getFullname().equals("")) {
            mView.showFormFullnameError("Full name can't be empty");
            valid = false;
        }

        return valid;
    }

    private void clearError() {
        mView.showFormUsernameError("");
        mView.showFormPasswordError("");
        mView.showFormFullnameError("");
    }
}
