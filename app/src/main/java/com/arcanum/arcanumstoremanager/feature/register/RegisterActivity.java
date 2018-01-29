package com.arcanum.arcanumstoremanager.feature.register;

import android.os.Bundle;
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
        mRouter.showLoggedScreen("test");
        mRouter.closeScreen();
    }

    @Override
    public void showError(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_SHORT).show();
    }

    @Click(R.id.register_button)
    public void onRegisterClicked() {
        mPresenter.registerUser(new User());
    }
}
