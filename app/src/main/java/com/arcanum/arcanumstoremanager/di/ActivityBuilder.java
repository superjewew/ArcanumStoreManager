package com.arcanum.arcanumstoremanager.di;

import com.arcanum.arcanumstoremanager.feature.attendance.AttendanceActivityModule;
import com.arcanum.arcanumstoremanager.feature.attendance.AttendanceActivity_;
import com.arcanum.arcanumstoremanager.feature.dashboard.DashboardActivityModule;
import com.arcanum.arcanumstoremanager.feature.dashboard.DashboardActivity_;
import com.arcanum.arcanumstoremanager.feature.logged.LoggedActivityModule;
import com.arcanum.arcanumstoremanager.feature.logged.LoggedActivity_;
import com.arcanum.arcanumstoremanager.feature.login.LoginActivityModule;
import com.arcanum.arcanumstoremanager.feature.login.LoginActivity_;
import com.arcanum.arcanumstoremanager.feature.register.RegisterActivityModule;
import com.arcanum.arcanumstoremanager.feature.register.RegisterActivity_;
import com.arcanum.arcanumstoremanager.feature.userdetail.AccountDetailActivityModule;
import com.arcanum.arcanumstoremanager.feature.userdetail.AccountDetailActivity_;
import com.arcanum.arcanumstoremanager.feature.useredit.AccountEditActivityModule;
import com.arcanum.arcanumstoremanager.feature.useredit.AccountEditActivity_;
import com.arcanum.arcanumstoremanager.feature.userslist.AccountsActivityModule;
import com.arcanum.arcanumstoremanager.feature.userslist.AccountsActivity_;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by norman on 24/01/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity_ bindLoginActivity();

    @ContributesAndroidInjector(modules = RegisterActivityModule.class)
    abstract RegisterActivity_ bindRegisterActivity();

    @ContributesAndroidInjector(modules = LoggedActivityModule.class)
    abstract LoggedActivity_ bindLoggedActivity();

    @ContributesAndroidInjector(modules = DashboardActivityModule.class)
    abstract DashboardActivity_ bindDashboardActivity();

    @ContributesAndroidInjector(modules = AttendanceActivityModule.class)
    abstract AttendanceActivity_ bindAttendanceActivity();

    @ContributesAndroidInjector(modules = AccountsActivityModule.class)
    abstract AccountsActivity_ bindAccountsActivity();

    @ContributesAndroidInjector(modules = AccountDetailActivityModule.class)
    abstract AccountDetailActivity_ bindAccountDetailActivity();

    @ContributesAndroidInjector(modules = AccountEditActivityModule.class)
    abstract AccountEditActivity_ bindAccountEditActivity();
}
