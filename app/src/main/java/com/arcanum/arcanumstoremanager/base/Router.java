package com.arcanum.arcanumstoremanager.base;

/**
 * Created by norman on 24/01/18.
 */

public interface Router {
    void closeScreen();
    void showRegisterScreen();
    void showAdminScreen();
    void showLoggedScreen(String username);
    void showVisitsScreen();
    void showSalesScreen();
    void showInventoryScreen();
    void showProductDetailScreen();
    void showUsersScreen();
    void showUserDetailScreen();
}
