package com.arcanum.arcanumstoremanager.feature;

import android.app.Activity;

import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.feature.dashboard.DashboardActivity_;
import com.arcanum.arcanumstoremanager.feature.logged.LoggedActivity_;
import com.arcanum.arcanumstoremanager.feature.register.RegisterActivity_;

/**
 * Created by norman on 24/01/18.
 */

public class RouterImpl implements Router {

    private final Activity activity;

    public RouterImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void closeScreen() {
        activity.finish();
    }

    @Override
    public void showRegisterScreen() {
        RegisterActivity_.intent(activity).start();
    }

    @Override
    public void showAdminScreen() {
        DashboardActivity_.intent(activity).start();
    }

    @Override
    public void showLoggedScreen(String username) {
        LoggedActivity_.intent(activity).username(username).start();
    }

    @Override
    public void showVisitsScreen() {

    }

    @Override
    public void showSalesScreen() {

    }

    @Override
    public void showInventoryScreen() {

    }

    @Override
    public void showProductDetailScreen() {

    }

    @Override
    public void showUsersScreen() {

    }

    @Override
    public void showUserDetailScreen() {

    }
}
