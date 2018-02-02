package com.arcanum.arcanumstoremanager.feature.useredit;

import com.arcanum.arcanumstoremanager.domain.entity.User;

/**
 * Created by norman on 01/02/18.
 */

public interface AccountEditContract {
    interface View {
        void initViewContent(User user);
        void showError(String message);
        void closeScreen();
    }

    interface Presenter {
        void loadUser(int id);
        void updateUser(User user);
    }
}
