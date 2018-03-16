package com.arcanum.arcanumstoremanager.feature;

import android.app.Activity;

import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.feature.attendance.AttendanceActivity_;
import com.arcanum.arcanumstoremanager.feature.dashboard.DashboardActivity_;
import com.arcanum.arcanumstoremanager.feature.logged.LoggedActivity_;
import com.arcanum.arcanumstoremanager.feature.register.RegisterActivity_;
import com.arcanum.arcanumstoremanager.feature.userdetail.AccountDetailActivity_;
import com.arcanum.arcanumstoremanager.feature.useredit.AccountEditActivity_;
import com.arcanum.arcanumstoremanager.feature.userslist.AccountsActivity_;

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
    public void showRegisterScreen(String username) {
        RegisterActivity_.intent(activity).username(username).start();
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
        AttendanceActivity_.intent(activity).start();
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
    public void showAccountsScreen() {
        AccountsActivity_.intent(activity).start();
    }

    @Override
    public void showAccountDetailScreen(String username) {
        AccountDetailActivity_.intent(activity).username(username).start();
    }

    @Override
    public void showAccountEditScreen(String username) {
        AccountEditActivity_.intent(activity).username(username).start();
    }
}
