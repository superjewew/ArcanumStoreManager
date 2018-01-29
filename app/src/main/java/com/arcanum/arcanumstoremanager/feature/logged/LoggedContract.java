package com.arcanum.arcanumstoremanager.feature.logged;

/**
 * Created by norman on 29/01/18.
 */

public interface LoggedContract {
    interface View {

        void showError(String e);

        void closeScreen();
    }

    interface Presenter {

        void updateVisit(String username);
    }

}
