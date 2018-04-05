package com.arcanum.arcanumstoremanager.feature.home;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_home)
public class HomeActivity extends DaggerAppCompatActivity {

    @Inject
    Router router;

    @ViewById(R.id.message)
    TextView mTextMessage;

    @ViewById(R.id.navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    router.showProductList();
                    router.closeScreen();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    return true;
                case R.id.navigation_notifications:
                    router.showAccountsScreen();
                    router.closeScreen();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    return true;
            }
            return false;
        }
    };

    @AfterViews
    public void initAfterViews() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
