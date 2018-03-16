package com.arcanum.arcanumstoremanager.feature.useredit;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.entity.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_account_edit)
public class AccountEditActivity extends DaggerAppCompatActivity implements AccountEditContract.View {

    @ViewById(R.id.username)
    TextInputEditText usernameEt;

    @ViewById(R.id.fullname)
    TextInputEditText fullnameEt;

    @ViewById(R.id.pass_spinner)
    Spinner passSpin;

    @ViewById(R.id.email)
    EditText emailEt;

    @ViewById(R.id.phone)
    EditText phoneEt;

    @Inject
    AccountEditContract.Presenter presenter;

    @Inject
    Router router;

    @Extra
    String username;

    private ArrayAdapter<CharSequence> adapter;

    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void initAfterViews() {
        adapter = ArrayAdapter.createFromResource(this,
                R.array.pass_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        passSpin.setAdapter(adapter);

        presenter.loadUser(username);
    }

    @ItemSelect(R.id.pass_spinner)
    public void onPassTypeSelected(boolean selected, String passType) {
        user.setPassType(passType);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                updateUserFromField();
                presenter.updateUser(user);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initViewContent(User user) {
        this.user = user;
        usernameEt.setText(user.getUsername());
        fullnameEt.setText(user.getFullname());
        passSpin.setSelection(adapter.getPosition(user.getPassType()));
        emailEt.setText(user.getEmail());
        phoneEt.setText(user.getPhone());
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeScreen() {
        router.showAccountDetailScreen(user.getUsername());
        router.closeScreen();
    }

    private void updateUserFromField() {
        user.setUsername(usernameEt.getText().toString());
        user.setFullname(fullnameEt.getText().toString());
        user.setPhone(phoneEt.getText().toString());
        user.setEmail(emailEt.getText().toString());
    }
}
