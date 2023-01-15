package com.sakhshop.backend.security;

import java.io.Serial;
import java.io.Serializable;

public class HttpPath implements Serializable {

    @Serial
    private static final long serialVersionUID = 6699706956241530459L;

    public static final String[] API_USER = {
            "/api/auth/registrationUser", //
            "/api/auth/confirmEmailUser", //
            "/api/auth/resetPasswordUser",
            "/api/auth/checkServerTokenUserResetPassword", //
            "/api/auth/newPasswordUser", //
            "/api/auth/loginUser", //
    };
    public static final String API_SINGLETON_LOGIN_USER_PATH = "/api/auth/loginUser";
    public static final String API_SINGLETON_GUARD_USER_PATH = "/api/AccountGuard/user";
    public static final String API_SINGLETON_LOGIN_SELLER_PERSON_PATH = "/api/auth/loginSellerPerson";

    public static final String[] API_SELLER_PERSON = {
            "/api/auth/registrationSellerPerson", //
            "/api/auth/confirmEmailSellerPerson", //
            "/api/auth/resetPasswordSellerPerson", //
            "/api/auth/checkServerTokenSellerPersonResetPassword", //
            "/api/auth/newPasswordSellerPerson", //
            "/api/auth/loginSellerPerson", //
    };

    public static final String[] API_GENERAL_PERSON = {
            "/api/auth/registrationSeller", // Общий для seller
    };
    public static final String[] API_GENERAL = {
            "/api/checkShopNameRegistrationSellerPersonRequest",
    };

    public static final String[] API_ACCOUNT_GUARD_USER = {
            "/api/AccountGuard/user",
    };

    public static final String[] API_ACCOUNT_GUARD_PERSON = {
            "/api/AccountGuard/seller",
    };


    public static final String[] API_ALL = {
            "/api/all"
    };

    public static final String[] API_PASSPORT = {
            "/resources/ResourcesGuard/passport/image/**"
    };
}
