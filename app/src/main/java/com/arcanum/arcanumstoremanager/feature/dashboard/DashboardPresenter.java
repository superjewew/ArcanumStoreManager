package com.arcanum.arcanumstoremanager.feature.dashboard;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.data.VisitDao.VisitWithName;
import com.arcanum.arcanumstoremanager.domain.usecase.GetVisitsUseCase;
import com.arcanum.arcanumstoremanager.feature.dashboard.DashboardContract.VisitFilterType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 24/01/18.
 */

public class DashboardPresenter extends BasePresenter<DashboardContract.View> implements DashboardContract.Presenter {

    @Inject
    GetVisitsUseCase useCase;

    @Inject
    public DashboardPresenter(DashboardContract.View view, GetVisitsUseCase useCase) {
        attachView(view);
        this.useCase = useCase;
    }

    @Override
    public void loadData(VisitFilterType filter) {
        useCase.execute()
                .map(list -> filterList(list, filter))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess);

        mView.setSalesAmount(0);
    }

    private List<VisitWithName> filterList(List<VisitWithName> list, VisitFilterType filterType) {
        Calendar start = createStartDateBasedOnFilter(filterType);
        Calendar end = createEndDateBasedOnFilter(filterType);

        List<VisitWithName> filtered = new ArrayList<>();

        for(VisitWithName v : list) {
            if(v.visittime < start.getTimeInMillis() || v.visittime > end.getTimeInMillis()) {
                filtered.add(v);
            }
        }

        list.removeAll(filtered);

        return list;
    }

    private void onSuccess(List<VisitWithName> visits) {
        mView.setVisitAmount(visits.size());
    }

    private Calendar createStartDateBasedOnFilter(VisitFilterType filterType) {
        Calendar cal = Calendar.getInstance();

        switch (filterType) {
            case WEEKLY:
                cal.set(Calendar.DAY_OF_WEEK, 1);
            case TODAY:
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
        }

        return cal;
    }

    private Calendar createEndDateBasedOnFilter(VisitFilterType filterType) {
        Calendar cal = Calendar.getInstance();

        switch (filterType) {
            case WEEKLY:
                cal.set(Calendar.DAY_OF_WEEK, 7);
            case TODAY:
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
        }

        return cal;
    }
}
