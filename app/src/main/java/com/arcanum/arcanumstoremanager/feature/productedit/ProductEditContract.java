package com.arcanum.arcanumstoremanager.feature.productedit;

import com.arcanum.arcanumstoremanager.domain.entity.Product;

/**
 * Created by norman on 16/03/18.
 */

public interface ProductEditContract {
    interface View {
        void showProduct(Product item);

        void showError(String message);

        void showNameError(boolean show);

        void showPriceError(boolean show);

        void showCodeError(boolean show);

        void showStockError(boolean show);

        void exit();
    }

    interface Presenter {
        void loadProduct(String code);

        void saveProduct(Product item);
    }
}
