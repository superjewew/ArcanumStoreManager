package com.arcanum.arcanumstoremanager.feature.userslist;

import com.arcanum.arcanumstoremanager.domain.entity.User;

import java.util.List;

/**
 * Created by norman on 31/01/18.
 */

public interface AccountsContract {
    interface View {
        void updateAdapter(List<User> users);
    }

    interface Presenter {
        void loadData();
    }
}
