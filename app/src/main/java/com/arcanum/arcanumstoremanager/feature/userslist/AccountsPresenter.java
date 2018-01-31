package com.arcanum.arcanumstoremanager.feature.userslist;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.domain.usecase.GetAllUserUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.GetUserUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 31/01/18.
 */

public class AccountsPresenter extends BasePresenter<AccountsContract.View> implements AccountsContract.Presenter {

    @Inject
    GetAllUserUseCase getAllUserUseCase;

    @Inject
    public AccountsPresenter(AccountsContract.View mainView, GetAllUserUseCase userUseCase) {
        attachView(mainView);
        getAllUserUseCase = userUseCase;
    }

    @Override
    public void loadData() {
        getAllUserUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void onFailed(Throwable throwable) {

    }

    private void onSuccess(List<User> users) {
        mView.updateAdapter(users);
    }
}
