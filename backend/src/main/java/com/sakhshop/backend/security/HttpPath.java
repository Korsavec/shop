package com.sakhshop.backend.security;

public class HttpPath {


    // Использую константы потому что они одиночно используются в UserDetailsServiceImpl
    public static final String API_SINGLETON_LOGIN_USER = "/api/auth/loginUser";
    public static final String API_SINGLETON_GUARD_USER = "/api/AccountGuard/user";


    private HttpPath() {
    }

    protected static final String[] API_USER = {
            "/api/auth/registrationUser",
            "/api/auth/confirmEmailUser",
            "/api/auth/resetPasswordUser",
            "/api/auth/checkTokenUserResetPassword",
            "/api/auth/newPasswordUser",
            API_SINGLETON_LOGIN_USER
    };

    protected static final String[] API_SELLER = {
            "/api/auth/registrationSeller",
            "/api/auth/confirmEmailSeller",
            "/api/auth/resetPasswordSeller",
            "/api/auth/checkTokenSellerResetPassword",
            "/api/auth/newPasswordSeller",
            "/api/auth/loginSeller",
    };


    protected static final String[] API_GENERAL = {
            "/api/checkShopName",
            "/api/all"
    };

    protected static final String[] API_ACCOUNT_GUARD_USER = {
            API_SINGLETON_GUARD_USER
    };

    protected static final String[] API_ACCOUNT_GUARD_SELLER = {
            "/api/AccountGuard/seller",
    };

    protected static final String[] API_GUARD_PASSPORT = {
            "/resources/ResourcesGuard/passport/image/**"
    };



}
