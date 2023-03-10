package com.sakhshop.backend.security;

public class HttpPath {


    // Использую константы потому что они одиночно используются в UserDetailsServiceImpl
    public static final String API_SINGLETON_LOGIN_USER = "/api/auth/loginUser";
    public static final String API_SINGLETON_GUARD_USER = "/api/AccountGuard/user";


    public static final String API_SINGLETON_LOGIN_SELLER = "/api/auth/loginSeller";
    public static final String API_SINGLETON_GUARD_SELLER = "/api/AccountGuard/seller";


    public static final String API_SINGLETON_LOGIN_ADMIN = "/api/auth/loginAdmin";
    public static final String API_SINGLETON_GUARD_ADMIN = "/api/AccountGuard/admin";


    public static final String API_SINGLETON_LOGIN_LOGISTICS_COMPANY = "/api/auth/loginLogisticsCompany";
    public static final String API_SINGLETON_GUARD_LOGISTICS_COMPANY = "/api/AccountGuard/logisticsCompany";


    public static final String API_SINGLETON_LOGIN_LOGISTICS_PERSON = "/api/auth/loginLogisticsPerson";
    public static final String API_SINGLETON_GUARD_LOGISTICS_PERSON = "/api/AccountGuard/logisticsPerson";




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

    protected static final String[] API_ACCOUNT_GUARD_USER = {
            API_SINGLETON_GUARD_USER
    };

    //---

    protected static final String[] API_SELLER = {
            "/api/auth/registrationSeller",
            "/api/auth/confirmEmailSeller",
            "/api/auth/resetPasswordSeller",
            "/api/auth/checkTokenSellerResetPassword",
            "/api/auth/newPasswordSeller",
            API_SINGLETON_LOGIN_SELLER,
    };

    protected static final String[] API_ACCOUNT_GUARD_SELLER = {
            API_SINGLETON_GUARD_SELLER,
    };

    //---

    protected static final String[] API_ADMIN = {
            "/api/auth/resetPasswordAdmin",
            "/api/auth/checkTokenAdminResetPassword",
            "/api/auth/newPasswordAdmin",
            API_SINGLETON_LOGIN_ADMIN
    };

    protected static final String[] API_ACCOUNT_GUARD_ADMIN = {
            API_SINGLETON_GUARD_ADMIN,
    };

    //---

    protected static final String[] LOGISTICS_COMPANY = {
            "/api/auth/registrationLogisticsCompany",
            "/api/auth/confirmEmailLogisticsCompany",
            "/api/auth/resetPasswordLogisticsCompany",
            "/api/auth/checkTokenLogisticsCompanyResetPassword",
            "/api/auth/newPasswordLogisticsCompany",
            API_SINGLETON_LOGIN_LOGISTICS_COMPANY
    };

    protected static final String[] API_ACCOUNT_GUARD_LOGISTICS_COMPANY = {
            API_SINGLETON_GUARD_LOGISTICS_COMPANY,
    };

    //---

    protected static final String[] LOGISTICS_PERSON = {
            "/api/auth/registrationLogisticsPerson",
            "/api/auth/confirmEmailLogisticsPerson",
            "/api/auth/resetPasswordLogisticsPerson",
            "/api/auth/checkTokenLogisticsPersonResetPassword",
            "/api/auth/newPasswordLogisticsPerson",
            API_SINGLETON_LOGIN_LOGISTICS_PERSON
    };

    protected static final String[] API_ACCOUNT_GUARD_LOGISTICS_PERSON = {
            API_SINGLETON_GUARD_LOGISTICS_PERSON,
    };

    //---

    protected static final String[] API_GENERAL = {
            "/api/checkShopName",
            "/api/all"
    };

    //---

    protected static final String[] API_GUARD_PASSPORT = {
            "/resources/Guard/image/passport/**"
    };



}
