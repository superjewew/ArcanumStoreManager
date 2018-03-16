package com.arcanum.arcanumstoremanager.feature.productlist;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arcanum.arcanumstoremanager.R;
import com.arcanum.arcanumstoremanager.domain.entity.Product;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by norman on 16/03/18.
 */

@EViewGroup(R.layout.rowview_product)
public class ProductListViewHolder extends RelativeLayout {

    @ViewById(R.id.product_name_tv)
    TextView productNameTv;

    @ViewById(R.id.product_price_tv)
    TextView productPriceTv;

    @ViewById(R.id.product_demo_status_tv)
    TextView productDemoTv;

    public ProductListViewHolder(Context context) {
        super(context);
    }

    public void bindData(Product product) {
        productNameTv.setText(product.getName());
        productPriceTv.setText("" + product.getPrice());
        productDemoTv.setText(product.isDemoAvailable() ? "Demo Available" : "No Demo");
    }
}
