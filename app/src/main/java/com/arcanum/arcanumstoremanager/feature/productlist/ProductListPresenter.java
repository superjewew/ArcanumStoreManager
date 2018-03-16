package com.arcanum.arcanumstoremanager.feature.productlist;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.entity.Product;
import com.arcanum.arcanumstoremanager.domain.usecase.product.GetAllProductUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 16/03/18.
 */

public class ProductListPresenter extends BasePresenter<ProductListContract.View> implements ProductListContract.Presenter {

    GetAllProductUseCase useCase;

    @Inject
    public ProductListPresenter(ProductListContract.View view, GetAllProductUseCase useCase) {
        attachView(view);
        this.useCase = useCase;
    }

    @Override
    public void loadProducts() {
        useCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void onFailed(Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }

    private void onSuccess(List<Product> products) {
        mView.showProducts(products);
    }
}
