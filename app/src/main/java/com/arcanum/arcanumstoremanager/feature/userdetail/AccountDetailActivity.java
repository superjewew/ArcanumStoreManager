package com.arcanum.arcanumstoremanager.feature.userdetail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.entity.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.arcanum.arcanumstoremanager.feature.dashboard.DashboardContract.VisitFilterType.TODAY;
import static com.arcanum.arcanumstoremanager.feature.dashboard.DashboardContract.VisitFilterType.WEEKLY;

@EActivity(R.layout.activity_account_detail)
public class AccountDetailActivity extends DaggerAppCompatActivity implements AccountDetailContract.View {

    @ViewById(R.id.username)
    TextView usernameTv;

    @ViewById(R.id.fullname)
    TextView fullnameTv;

    @ViewById(R.id.email)
    TextView emailTv;

    @ViewById(R.id.phone)
    TextView phoneTv;

    @Inject
    AccountDetailContract.Presenter presenter;

    @Inject
    Router router;

    @Extra
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void initAfterExtras() {
        presenter.loadUser(userId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                router.showAccountEditScreen(userId);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(User user) {
        usernameTv.setText(user.getUsername());
        fullnameTv.setText(user.getFullname());
        emailTv.setText(user.getEmail());
        phoneTv.setText(user.getPhone());
    }
}
