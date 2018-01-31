package com.arcanum.arcanumstoremanager.feature.dashboard;

/**
 * Created by norman on 24/01/18.
 */

public interface DashboardContract {

    enum VisitFilterType {
        TODAY,
        WEEKLY,
        MONTHLY,
        ALLTIME
    }

    interface View {
        void setVisitAmount(int visit);
        void setSalesAmount(int sales);
        void showVisitsDetail();
        void showSalesDetail();
        void showInventoryScreen();
    }

    interface Presenter {
        void loadData(VisitFilterType filter);
    }
}
