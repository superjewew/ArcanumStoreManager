package com.arcanum.arcanumstoremanager.feature.dashboard;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;
import com.arcanum.arcanumstoremanager.domain.usecase.GetVisitsUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 24/01/18.
 */

public class DashboardPresenter extends BasePresenter<DashboardContract.View> implements DashboardContract.Presenter {

    GetVisitsUseCase getVisitsUseCase;

    @Inject
    public DashboardPresenter(DashboardContract.View view, GetVisitsUseCase useCase) {
        attachView(view);
        getVisitsUseCase = useCase;
    }

    @Override
    public void loadData() {
        getVisitsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess);
        mView.setSalesAmount(10);
    }

    private void onSuccess(List<Visit> visits) {
        mView.setVisitAmount(visits.size());
    }
}
