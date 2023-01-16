package com.sakhshop.backend.security;

public class HttpPath {

    private HttpPath() {
    }

    protected static final String[] API_USER = {
            "/api/auth/registrationUser", //
            "/api/auth/confirmEmailUser", //
            "/api/auth/resetPasswordUser",
            "/api/auth/checkServerTokenUserResetPassword", //
            "/api/auth/newPasswordUser", //
            "/api/auth/loginUser", //
    };


    public static final String API_SINGLETON_LOGIN_USER_PATH = "/api/auth/loginUser";
    public static final String API_SINGLETON_GUARD_USER_PATH = "/api/AccountGuard/user";

//    public static final String API_SINGLETON_LOGIN_SELLER_PERSON_PATH = "/api/auth/loginSellerPerson"

    protected static final String[] API_SELLER_PERSON = {
            "/api/auth/registrationSellerPerson", //
            "/api/auth/confirmEmailSellerPerson", //
            "/api/auth/resetPasswordSellerPerson", //
            "/api/auth/checkServerTokenSellerPersonResetPassword", //
            "/api/auth/newPasswordSellerPerson", //
            "/api/auth/loginSellerPerson", //
    };

    protected static final String[] API_GENERAL_PERSON = {
            "/api/auth/registrationSeller", // Общий для seller
    };
    protected static final String[] API_GENERAL = {
            "/api/checkShopNameRegistrationSellerPersonRequest",
    };

    protected static final String[] API_ACCOUNT_GUARD_USER = {
            "/api/AccountGuard/user",
    };

    protected static final String[] API_ACCOUNT_GUARD_PERSON = {
            "/api/AccountGuard/seller",
    };


    protected static final String[] API_ALL = {
            "/api/all"
    };

    protected static final String[] API_PASSPORT = {
            "/resources/ResourcesGuard/passport/image/**"
    };


}
