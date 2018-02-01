package com.arcanum.arcanumstoremanager.feature.useredit;

import android.os.Bundle;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.entity.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_account_edit)
public class AccountEditActivity extends DaggerAppCompatActivity implements AccountEditContract.View {

    @Inject
    AccountEditContract.Presenter presenter;

    @Inject
    Router router;

    @Extra
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void initAfterViews() {
        presenter.loadUser(username);
    }

    @Override
    public void initViewContent(User user) {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeScreen() {
        router.closeScreen();
    }
}
