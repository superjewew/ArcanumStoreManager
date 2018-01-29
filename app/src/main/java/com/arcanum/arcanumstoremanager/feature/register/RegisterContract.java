package com.arcanum.arcanumstoremanager.feature.register;

import com.arcanum.arcanumstoremanager.domain.entity.User;

/**
 * Created by norman on 24/01/18.
 */

public interface RegisterContract {

    interface View {
        void showLoggedScreen();

        void showError(String reason);
    }

    interface Presenter {
        void registerUser(User user);
    }
}
