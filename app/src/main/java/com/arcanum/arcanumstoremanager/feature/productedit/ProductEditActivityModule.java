package com.arcanum.arcanumstoremanager.feature.productedit;

import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.usecase.product.GetProductUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.product.UpdateProductUseCase;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 16/03/18.
 */

@Module
public class ProductEditActivityModule {

    @Provides
    Router provideRouter(ProductEditActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    ProductEditContract.View provideMainView(ProductEditActivity_ activity){
        return activity;
    }

    @Provides
    ProductEditContract.Presenter provideMainPresenter(ProductEditContract.View mainView,
                                                       GetProductUseCase productUseCase,
                                                       UpdateProductUseCase updateProductUseCase) {
        return new ProductEditPresenter(mainView, productUseCase, updateProductUseCase);
    }
}
