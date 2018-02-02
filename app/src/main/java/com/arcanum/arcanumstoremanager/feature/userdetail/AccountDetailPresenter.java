package com.arcanum.arcanumstoremanager.feature.userdetail;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserByIdUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 01/02/18.
 */

public class AccountDetailPresenter extends BasePresenter<AccountDetailContract.View> implements AccountDetailContract.Presenter {

    private GetUserByIdUseCase getUserUseCase;

    @Inject
    public AccountDetailPresenter(AccountDetailContract.View view,
                                  GetUserByIdUseCase getUseCase) {
        attachView(view);
        getUserUseCase = getUseCase;
    }

    @Override
    public void loadUser(int id) {
        getUserUseCase.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void onFailed(Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }

    private void onSuccess(User user) {
        mView.updateView(user);
    }
}
