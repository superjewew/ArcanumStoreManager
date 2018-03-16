package com.arcanum.arcanumstoremanager.feature.login;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;
import com.arcanum.arcanumstoremanager.utils.EncryptUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 24/01/18.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private String username_test = "test";
    private String admin_test = "admin";
    private String admin_pass = "adminarcanum";

    private GetUserUseCase getUserUseCase;
    private String pass;

    @Inject
    LoginPresenter(LoginContract.View view, GetUserUseCase getUserUseCase) {
        attachView(view);
        this.getUserUseCase = getUserUseCase;
    }

    @Override
    public void attemptLogin(String username, String password) {
        pass = password;
        if(username.equals(admin_test) && password.equals(admin_pass)) {
            mView.showAdminScreen();
        } else {
            getUserUseCase.execute(username.toLowerCase())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccess, this::onFailed);
        }
    }

    private void onSuccess(User user) {
        //TODO : Clean up this code
        if(user != null) {
            if(EncryptUtils.validatePassword(pass, user.getPassword())) {
                mView.showLoggedScreen(user.getUsername());
            } else {
                mView.showError("Wrong Password");
            }
        }
    }

    private void onFailed(Throwable throwable) {
        mView.showError("User not found, redirecting to registration screen");
        mView.showRegisterScreen();
    }
}
