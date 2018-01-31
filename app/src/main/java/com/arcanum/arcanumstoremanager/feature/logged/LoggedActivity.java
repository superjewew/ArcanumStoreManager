package com.arcanum.arcanumstoremanager.feature.logged;

import android.os.Bundle;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_logged)
public class LoggedActivity extends DaggerAppCompatActivity implements LoggedContract.View {

    @Inject
    LoggedPresenter mPresenter;

    @Inject
    Router mRouter;

    @Extra
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.done_button)
    public void onDoneClicked() {
        mPresenter.updateVisit(username);
    }

    @Override
    public void closeScreen() {
        mRouter.closeScreen();
    }

    @Override
    public void showError(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }
}
