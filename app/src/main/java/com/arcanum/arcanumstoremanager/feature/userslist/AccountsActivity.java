package com.arcanum.arcanumstoremanager.feature.userslist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.domain.entity.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_accounts)
public class AccountsActivity extends DaggerAppCompatActivity implements AccountsContract.View {

    @ViewById(R.id.account_list)
    RecyclerView accountList;

    @Inject
    AccountsContract.Presenter mPresenter;

    private AccountsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void initAfterView() {
        accountList.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.loadData();
    }

    @Override
    public void updateAdapter(List<User> users) {
        adapter = AccountsAdapter_.getInstance_(this);
        adapter.initItems(users);
        accountList.setAdapter(adapter);
    }
}
