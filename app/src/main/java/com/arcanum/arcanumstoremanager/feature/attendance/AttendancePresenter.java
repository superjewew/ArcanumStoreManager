package com.arcanum.arcanumstoremanager.feature.attendance;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.entity.Visit;
import com.arcanum.arcanumstoremanager.domain.usecase.GetVisitsUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 29/01/18.
 */

public class AttendancePresenter extends BasePresenter<AttendanceContract.View> implements AttendanceContract.Presenter {

    @Inject
    GetVisitsUseCase getVisitsUseCase;

    @Inject
    AttendancePresenter(AttendanceContract.View mainView, GetVisitsUseCase useCase) {
        attachView(mainView);
        getVisitsUseCase = useCase;
    }

    @Override
    public void getAttendance() {
        getVisitsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void onSuccess(List<Visit> visits) {
        mView.updateAdapter(visits);
    }

    private void onFailed(Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }

}
