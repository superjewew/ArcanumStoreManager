package com.arcanum.arcanumstoremanager.feature.login;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 24/01/18.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private String username_test = "test";
    private String admin_test = "admin";
    private String password_test = "1234";

    private GetUserUseCase getUserUseCase;

    @Inject
    LoginPresenter(LoginContract.View view, GetUserUseCase getUserUseCase) {
        attachView(view);
        this.getUserUseCase = getUserUseCase;
    }

    @Override
    public void attemptLogin(String email, String password) {
        getUserUseCase.execute(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void onSuccess(User user) {
        if(user.getName().equals(username_test)) {
            mView.showLoggedScreen(user.getName());
        } else if(user.getName().equals(admin_test)) {
            mView.showAdminScreen();
        } else {
            mView.showRegisterScreen();
        }
    }

    private void onFailed(Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }
}
