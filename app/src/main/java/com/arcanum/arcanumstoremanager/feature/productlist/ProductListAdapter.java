package com.arcanum.arcanumstoremanager.feature.productlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.arcanum.arcanumstoremanager.base.BaseRecyclerViewAdapter;
import com.arcanum.arcanumstoremanager.base.ViewWrapper;
import com.arcanum.arcanumstoremanager.domain.entity.Product;
import com.arcanum.arcanumstoremanager.domain.entity.User;
import com.arcanum.arcanumstoremanager.feature.userslist.AccountsViewHolder;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by norman on 16/03/18.
 */

@EBean
public class ProductListAdapter extends BaseRecyclerViewAdapter<Product, ProductListViewHolder> {

    @RootContext
    Context context;

    View.OnClickListener listener;

    @Override
    protected ProductListViewHolder onCreateItemView(ViewGroup parent, int viewType) {
        return ProductListViewHolder_.build(context);
    }

    @Override
    protected void initItems(List<Product> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ProductListViewHolder> holder, int position) {
        ProductListViewHolder view = holder.getView();
        Product product = items.get(position);

        setOnClickListener(view, product);

        view.bindData(product);
    }

    private void setOnClickListener(ProductListViewHolder view, Product product) {
        view.setTag(product);
        view.setOnClickListener(listener);
    }
}
