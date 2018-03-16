package com.arcanum.arcanumstoremanager.feature.productedit;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.entity.Product;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by norman on 16/03/18.
 */

@EActivity(R.layout.activity_product_edit)
public class ProductEditActivity extends DaggerAppCompatActivity implements ProductEditContract.View {

    @ViewById(R.id.product_name_layout)
    TextInputLayout productNameTil;

    @ViewById(R.id.product_price_layout)
    TextInputLayout productPriceTil;

    @ViewById(R.id.product_code_layout)
    TextInputLayout productCodeTil;

    @ViewById(R.id.product_stock_layout)
    TextInputLayout productStockTil;

    @ViewById(R.id.product_name_et)
    TextInputEditText productNameEt;

    @ViewById(R.id.product_price_et)
    TextInputEditText productPriceEt;

    @ViewById(R.id.product_code_et)
    TextInputEditText productCodeEt;

    @ViewById(R.id.product_stock_et)
    TextInputEditText productStockEt;

    @ViewById(R.id.demo_switch)
    Switch productDemoSw;

    @Inject
    ProductEditContract.Presenter mPresenter;

    @Inject
    Router router;

    @Extra
    String productCode;

    @AfterViews
    public void initAfterViews() {
        if(productCode != null && !productCode.equals("")) {
            mPresenter.loadProduct(productCode);
        }
    }

    @Override
    public void showProduct(Product item) {
        productNameEt.setText(item.getName());
        productPriceEt.setText("" + item.getPrice());
        productCodeEt.setText("" + item.getCode());
        productStockEt.setText("" + item.getStock());
        productDemoSw.setChecked(item.isDemoAvailable());
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNameError(boolean show) {
        if(show) {
            productNameTil.setError("Nama barang harus diisi");
        } else {
            productNameTil.setError("");
        }
    }

    @Override
    public void showPriceError(boolean show) {
        if(show) {
            productPriceTil.setError("Harga barang harus diisi");
        } else {
            productPriceTil.setError("");
        }
    }

    @Override
    public void showCodeError(boolean show) {
        if(show) {
            productCodeTil.setError("Code barang harus diisi");
        } else {
            productCodeTil.setError("");
        }
    }

    @Override
    public void showStockError(boolean show) {
        if(show) {
            productStockTil.setError("Stok barang harus diisi");
        } else {
            productStockTil.setError("");
        }
    }

    @Override
    public void exit() {
        router.closeScreen();
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
                mPresenter.saveProduct(createProductFromField());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Product createProductFromField() {
        Product product = new Product();
        product.setName(productNameEt.getText().toString());
        try {
            product.setPrice(Double.parseDouble(productPriceEt.getText().toString()));
        } catch(NumberFormatException e) {
            showPriceError(true);
        }
        product.setCode(productCodeEt.getText().toString());
        try {
            product.setStock(Integer.parseInt(productStockEt.getText().toString()));
        } catch(NumberFormatException e) {
            showStockError(true);
        }
        product.setDemoAvailable(productDemoSw.isChecked());
        return product;
    }

}
