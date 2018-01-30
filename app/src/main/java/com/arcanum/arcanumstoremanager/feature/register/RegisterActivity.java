package com.arcanum.arcanumstoremanager.feature.register;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.base.Router;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends DaggerAppCompatActivity implements RegisterContract.View{

    @ViewById(R.id.register_button)
    Button registerButton;

    @ViewById(R.id.username_layout)
    TextInputLayout usernameTil;

    @ViewById(R.id.password_layout)
    TextInputLayout passwordTil;

    @ViewById(R.id.fullname_layout)
    TextInputLayout fullnameTil;

    @ViewById(R.id.username)
    TextInputEditText usernameEt;

    @ViewById(R.id.password)
    TextInputEditText passwordEt;

    @ViewById(R.id.fullname)
    TextInputEditText fullnameEt;

    @ViewById(R.id.email)
    TextInputEditText emailEt;

    @ViewById(R.id.phone)
    TextInputEditText phoneEt;

    @ViewById(R.id.dob)
    TextInputEditText dobEt;

    @Inject
    RegisterContract.Presenter mPresenter;

    @Inject
    Router mRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showLoggedScreen() {
        mRouter.showLoggedScreen(usernameEt.getText().toString());
        mRouter.closeScreen();
    }

    @Override
    public void showError(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFormUsernameError(String message) {
        usernameTil.setError(message);
    }

    @Override
    public void showFormPasswordError(String message) {
        passwordTil.setError(message);
    }

    @Override
    public void showFormFullnameError(String message) {
        fullnameTil.setError(message);
    }

    @Click(R.id.register_button)
    public void onRegisterClicked() {
        User user = createUserFromField();
        mPresenter.registerUser(user);
    }

    private User createUserFromField() {
        return new User.Builder()
                .username(usernameEt.getText().toString())
                .password(passwordEt.getText().toString())
                .fullname(fullnameEt.getText().toString())
                .email(emailEt.getText().toString())
                .phone(phoneEt.getText().toString())
                .build();
    }
}
