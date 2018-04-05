package com.arcanum.arcanumstoremanager.base;

/**
 * Created by norman on 24/01/18.
 */

public interface Router {
    void closeScreen();

    void showRegisterScreen(String username);

    void showAdminScreen();

    void showLoggedScreen(String username);

    void showVisitsScreen();

    void showSalesScreen();

    void showProductList();

    void showProductDetailScreen();

    void showProductEditScreen(String productCode);

    void showAccountsScreen();

    void showAccountDetailScreen(String username);

    void showAccountEditScreen(String username);

    void showHomeScreen();
}
