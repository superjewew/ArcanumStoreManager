package com.arcanum.arcanumstoremanager.feature.login;

/**
 * Created by norman on 24/01/18.
 */

public interface LoginContract {
    interface View {
        void showRegisterScreen();
        void showLoggedScreen(String username);
        void showAdminScreen();
        void showError(String e);
    }

    interface Presenter {

        void attemptLogin(String email, String password);
    }
}
