package com.arcanum.arcanumstoremanager.feature.attendance;

import com.arcanum.arcanumstoremanager.domain.entity.Visit;

import java.util.List;

/**
 * Created by norman on 24/01/18.
 */

public interface AttendanceContract {
    interface View {

        void showError(String message);

        void updateAdapter(List<Visit> visits);
    }

    interface Presenter {
        void getAttendance();
    }
}
