package com.arcanum.arcanumstoremanager.feature.userdetail;

import com.arcanum.arcanumstoremanager.domain.entity.User;

/**
 * Created by norman on 01/02/18.
 */

public interface AccountDetailContract {

    interface View {
        void updateView(User user);
        void showError(String message);
    }

    interface Presenter {
        void loadUser(String username);
    }
}
