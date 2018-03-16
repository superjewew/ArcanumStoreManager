package com.arcanum.arcanumstoremanager.feature.productlist;

import com.arcanum.arcanumstoremanager.domain.entity.Product;

import java.util.List;

/**
 * Created by norman on 16/03/18.
 */

public interface ProductListContract {
    interface View {
        void showProducts(List<Product> productList);

        void showError(String message);
    }

    interface Presenter {
        void loadProducts();
    }
}
