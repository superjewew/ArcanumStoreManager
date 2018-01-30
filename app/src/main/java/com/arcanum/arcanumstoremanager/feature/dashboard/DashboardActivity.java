package com.arcanum.arcanumstoremanager.feature.dashboard;

import android.os.Bundle;
import android.widget.TextView;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@EActivity(R.layout.activity_dashboard)
public class DashboardActivity extends DaggerAppCompatActivity implements DashboardContract.View {

    @ViewById(R.id.visit_amount_tv)
    TextView mVisitAmountTv;

    @ViewById(R.id.sales_amount_tv)
    TextView mSalesAmountTv;

    @Inject
    DashboardContract.Presenter mPresenter;

    @Inject
    Router mRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void initAfterViews() {
        mPresenter.loadData();
    }

    @Override
    public void setVisitAmount(int visit) {
        mVisitAmountTv.setText("" + visit);
    }

    @Override
    public void setSalesAmount(int sales) {
        mSalesAmountTv.setText("" + sales);
    }

    @Click(R.id.visit_detail_tv)
    @Override
    public void showVisitsDetail() {
        mRouter.showVisitsScreen();
    }

    @Override
    public void showSalesDetail() {
        mRouter.showSalesScreen();
    }

    @Override
    public void showInventoryScreen() {
        mRouter.showInventoryScreen();
    }
}
