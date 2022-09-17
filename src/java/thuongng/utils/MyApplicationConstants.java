/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.utils;

/**
 *
 * @author Nhan
 */
public class MyApplicationConstants {

    public class DispatchFeatures {

        public static final String LOGIN_PAGE = "loginPage";
        public static final String LOGIN_CONTROLLER = "login";
        public static final String STARTUP_CONTROLLER ="";
    }
    public class LoginFeatures{
        public static final String INVALID_PAGE = "invalidPage";
        
    }

    public class SearchFeatures {
        public static final String STATIC_SEARCH_PAGE = "staticSearchPage";
        public static final String SEARCH_PAGE = "searchPage";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastname";
    }

    public class DeteleFeatures {

        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccount";
    }
    public class ErrorFeatures{
         public static final String ERROR_PAGE = "errorPage";
    }
    public class CartFeatures {

        public static final String ADD_BOOK_TO_CART_CONTROLLER = "addBook";
        public static final String DELETE_ITEMS_FROM_CART = "deleteBookFromCart";
        public static final String SHOW_CART_PAGE = "viewProduct";
        public static final String SHOW_BOOK_CONTROLLER = "showAllProduct";
        public static final String CHECKOUT_CONTROLLER = "checkOut";
        public static final String ONL_SHOPPING = "onlShoppingPage";
    }

    public class SignUpFeatures {

        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccount";
        public static final String STATIC_CREATE_ACCOUNT_PAGE = "staticCreateAccountPage";
        public static final String CREATE_ACCOUNT_PAGE="createAccountPage";
    }

    public class UpdateFeatures {

        public static final String UPDATE_CONTROLLER = "updateAccount";
    }

    public class LogoutFeature {

        public static final String LOGOUT_CONTROLLER = "logout";
    }

}
