package com.arcanum.arcanumstoremanager.feature.login;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_login)
public class LoginActivity extends DaggerAppCompatActivity implements LoginContract.View {

    @ViewById(R.id.email)
    EditText mEmailEt;

    @ViewById(R.id.password)
    EditText mPasswordEt;

    @Inject
    LoginContract.Presenter mPresenter;

    @Inject
    Router mRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.email_sign_in_button)
    public void signInClicked() {
        String email = mEmailEt.getText().toString();
        String pass = mPasswordEt.getText().toString();
        mPresenter.attemptLogin(email, pass);
    }

    @Override
    public void showRegisterScreen() {
        mRouter.showRegisterScreen(mEmailEt.getText().toString());
    }

    @Override
    public void showLoggedScreen(String username) {
        mRouter.showLoggedScreen(username);
    }

    @Override
    public void showAdminScreen() {
        mRouter.showAdminScreen();
    }

    @Override
    public void showError(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }
}
