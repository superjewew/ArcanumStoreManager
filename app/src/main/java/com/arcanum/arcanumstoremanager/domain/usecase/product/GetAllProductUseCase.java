package com.arcanum.arcanumstoremanager.domain.usecase.product;

import com.arcanum.arcanumstoremanager.base.BaseUseCase;
import com.arcanum.arcanumstoremanager.domain.entity.Product;
import com.arcanum.arcanumstoremanager.domain.repo.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 16/03/18.
 */

public class GetAllProductUseCase implements BaseUseCase<List<Product>> {

    private ProductRepository repository;

    @Inject
    public GetAllProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<Product>> execute() {
        return repository.getAllProducts();
    }
}
