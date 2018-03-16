package com.arcanum.arcanumstoremanager.feature.productlist;

import com.arcanum.arcanumstoremanager.base.Router;
import com.arcanum.arcanumstoremanager.domain.usecase.product.GetAllProductUseCase;
import com.arcanum.arcanumstoremanager.feature.RouterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 16/03/18.
 */

@Module
public class ProductListActivityModule {

    @Provides
    Router provideRouter(ProductListActivity_ activity) {
        return new RouterImpl(activity);
    }

    @Provides
    ProductListContract.View provideMainView(ProductListActivity_ activity){
        return activity;
    }

    @Provides
    ProductListContract.Presenter provideMainPresenter(ProductListContract.View mainView, GetAllProductUseCase useCase){
        return new ProductListPresenter(mainView, useCase);
    }
}
