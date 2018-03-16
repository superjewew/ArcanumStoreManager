package com.arcanum.arcanumstoremanager.domain.repo;

import com.arcanum.arcanumstoremanager.domain.entity.Product;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public interface ProductRepository {
    Completable addProduct(Product item);
    Single<List<Product>> getAllProducts();
    Single<Product> getProduct(long productCode);
    Completable updateProduct(Product item);
    Completable deleteProduct(Product item);
}
