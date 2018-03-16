package com.arcanum.arcanumstoremanager.domain.usecase.product;

import com.arcanum.arcanumstoremanager.base.CompletableUseCase;
import com.arcanum.arcanumstoremanager.domain.entity.Product;
import com.arcanum.arcanumstoremanager.domain.repo.ProductRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 16/03/18.
 */

public class UpdateProductUseCase implements CompletableUseCase<Product> {

    private ProductRepository repository;

    @Inject
    public UpdateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable execute(Product param) {
        return repository.updateProduct(param);
    }
}
