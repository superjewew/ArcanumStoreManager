package com.arcanum.arcanumstoremanager.feature.productedit;

import com.arcanum.arcanumstoremanager.base.BasePresenter;
import com.arcanum.arcanumstoremanager.domain.entity.Product;
import com.arcanum.arcanumstoremanager.domain.usecase.product.GetProductUseCase;
import com.arcanum.arcanumstoremanager.domain.usecase.product.UpdateProductUseCase;
import com.arcanum.arcanumstoremanager.exception.EmptyNameException;
import com.arcanum.arcanumstoremanager.exception.EmptyProductCodeException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 16/03/18.
 */

public class ProductEditPresenter extends BasePresenter<ProductEditContract.View> implements ProductEditContract.Presenter {

    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductUseCase getProductUseCase;

    ProductEditPresenter(ProductEditContract.View view, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase) {
        attachView(view);
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
    }

    @Override
    public void loadProduct(String code) {
        getProductUseCase.execute(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onProductLoaded, this::onError);
    }

    @Override
    public void saveProduct(Product item) {
        clearError();

        try {
            updateProductUseCase.execute(item)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onProductSaved, this::onError);
        } catch (EmptyNameException e) {
            mView.showNameError(true);
        } catch (EmptyProductCodeException e) {
            mView.showCodeError(true);
        }
    }

    private void onProductLoaded(Product item) {
        mView.showProduct(item);
    }

    private void onProductSaved() {
        mView.exit();
    }

    private void onError(Throwable throwable) {
        mView.showError(throwable.getLocalizedMessage());
    }

    private void clearError() {
        mView.showNameError(false);
        mView.showPriceError(false);
        mView.showCodeError(false);
        mView.showStockError(false);
    }
}
