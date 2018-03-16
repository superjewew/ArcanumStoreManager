package com.arcanum.arcanumstoremanager.domain.usecase.product;

import com.arcanum.arcanumstoremanager.base.BaseUseCaseWithParam;
import com.arcanum.arcanumstoremanager.domain.entity.Product;
import com.arcanum.arcanumstoremanager.domain.repo.ProductRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 16/03/18.
 */

public class GetProductUseCase implements BaseUseCaseWithParam<Long, Product> {

    private ProductRepository repository;

    @Inject
    public GetProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<Product> execute(Long param) {
        return repository.getProduct(param);
    }
}
