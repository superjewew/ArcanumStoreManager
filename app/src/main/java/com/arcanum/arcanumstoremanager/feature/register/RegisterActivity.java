package com.arcanum.arcanumstoremanager.feature.register;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.utils.EncryptUtils;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.ViewById;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends DaggerAppCompatActivity implements RegisterContract.View{

    @ViewById(R.id.register_button)
    Button registerButton;

    @ViewById(R.id.username_layout)
    TextInputLayout usernameTil;

    @ViewById(R.id.password_layout)
    TextInputLayout passwordTil;

    @ViewById(R.id.fullname_layout)
    TextInputLayout fullnameTil;

    @ViewById(R.id.username)
    TextInputEditText usernameEt;

    @ViewById(R.id.password)
    TextInputEditText passwordEt;

    @ViewById(R.id.fullname)
    TextInputEditText fullnameEt;

    @ViewById(R.id.email)
    TextInputEditText emailEt;

    @ViewById(R.id.phone)
    TextInputEditText phoneEt;

    @ViewById(R.id.dob)
    TextInputEditText dobEt;

    @ViewById(R.id.pass_spinner)
    AppCompatSpinner passSpin;

    @Inject
    RegisterContract.Presenter mPresenter;

    @Inject
    Router mRouter;

    @Extra
    String username;

    String passType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void initAfterExtras() {
        usernameEt.setText(username);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pass_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        passSpin.setAdapter(adapter);
    }

    @Override
    public void showLoggedScreen() {
        mRouter.showLoggedScreen(usernameEt.getText().toString());
        mRouter.closeScreen();
    }

    @Override
    public void showError(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFormUsernameError(String message) {
        usernameTil.setError(message);
    }

    @Override
    public void showFormPasswordError(String message) {
        passwordTil.setError(message);
    }

    @Override
    public void showFormFullnameError(String message) {
        fullnameTil.setError(message);
    }

    @Click(R.id.register_button)
    public void onRegisterClicked() {
        User user = createUserFromField();
        mPresenter.registerUser(user);
    }

    @ItemSelect(R.id.pass_spinner)
    public void onPassTypeSelected(boolean selected, String passType) {
        this.passType = passType;
    }

    private User createUserFromField() {
        return new User.Builder()
                .username(usernameEt.getText().toString().toLowerCase())
                .password(EncryptUtils.encrypt(passwordEt.getText().toString()))
                .fullname(fullnameEt.getText().toString())
                .email(emailEt.getText().toString())
                .phone(phoneEt.getText().toString())
                .passType(passType)
                .build();
    }
}
