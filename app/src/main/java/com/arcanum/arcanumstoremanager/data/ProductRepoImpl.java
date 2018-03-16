package com.arcanum.arcanumstoremanager.data;

import com.arcanum.arcanumstoremanager.domain.entity.Product;
import com.arcanum.arcanumstoremanager.domain.repo.ProductRepository;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import javax.inject.Inject;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 16/03/18.
 */

public class ProductRepoImpl implements ProductRepository {

    DatabaseReference productRemoteDb;

    @Inject
    public ProductRepoImpl(DatabaseReference productRemoteDb) {
        this.productRemoteDb = productRemoteDb;
    }

    @Override
    public Completable addProduct(Product item) {
        return Completable.defer(() -> innerAddProduct(item));
    }

    @Override
    public Single<List<Product>> getAllProducts() {
        return RxFirebaseDatabase
                .observeSingleValueEvent(productRemoteDb.child("product"), DataSnapshotMapper.listOf(Product.class))
                .toSingle();
    }

    @Override
    public Single<Product> getProduct(String productCode) {
        return Single.defer(() -> innerGetProduct(productCode));
    }

    @Override
    public Completable updateProduct(Product item) {
        return Completable.defer(() -> innerAddProduct(item));
    }

    @Override
    public Completable deleteProduct(Product item) {
        return null;
    }

    private Completable innerAddProduct(Product item) {
        return RxFirebaseDatabase.setValue(getProductDbReference(item.getCode()), item);
    }

    private Single<Product> innerGetProduct(String productCode) {
        return RxFirebaseDatabase
                .observeSingleValueEvent(getProductDbReference(productCode), Product.class)
                .toSingle();
    }

    private DatabaseReference getProductDbReference(String productCode) {
        return productRemoteDb.child("product").child("" + productCode);
    }
}
