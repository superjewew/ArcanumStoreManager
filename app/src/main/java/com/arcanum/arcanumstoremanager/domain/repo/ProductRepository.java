package com.arcanum.arcanumstoremanager.domain.repo;

import com.arcanum.arcanumstoremanager.domain.entity.ProductItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public interface ProductRepository {
    Completable createProduct(ProductItem item);
    Single<List<ProductItem>> getAllProducts();
    Completable updateProduct(ProductItem item);
}
