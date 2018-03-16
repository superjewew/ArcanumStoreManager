package com.arcanum.arcanumstoremanager.feature.productlist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.entity.Product;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by norman on 16/03/18.
 */

@EActivity(R.layout.activity_product_list)
public class ProductListActivity extends DaggerAppCompatActivity implements ProductListContract.View{

    @ViewById(R.id.product_list)
    RecyclerView productList;

    @Inject
    ProductListContract.Presenter mPresenter;

    @Inject
    Router router;

    private ProductListAdapter adapter;

    @AfterViews
    public void initAfterViews() {
        productList.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.loadProducts();
    }

    @Override
    public void showProducts(List<Product> products) {
        adapter = ProductListAdapter_.getInstance_(this);
        adapter.initItems(products);
        adapter.listener = v -> {

        };
        productList.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_product:
        }
        return super.onOptionsItemSelected(item);
    }
}
