package com.arcanum.arcanumstoremanager.feature.userslist;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.entity.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
@EActivity(R.layout.activity_accounts)
public class AccountsActivity extends DaggerAppCompatActivity implements AccountsContract.View {

    @ViewById(R.id.account_list)
    RecyclerView accountList;

    @Inject
    AccountsContract.Presenter mPresenter;

    @Inject
    Router router;

    private AccountsAdapter adapter;

    private List<User> users;

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
        this.users = users;
        adapter = AccountsAdapter_.getInstance_(this);
        adapter.initItems(users);
        adapter.listener = v -> {
            router.showAccountDetailScreen(((User) v.getTag()).id);
            router.closeScreen();
        };
        accountList.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_attendance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_export:
                AccountsActivityPermissionsDispatcher.onExportClickedWithPermissionCheck(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onExportClicked() {
        mPresenter.writeToCsv(users);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AccountsActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
