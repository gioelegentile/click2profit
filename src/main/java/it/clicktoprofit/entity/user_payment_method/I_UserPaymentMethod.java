package it.clicktoprofit.entity.user_payment_method;

/**
 * Created by Gioele on 31/12/2015.
 */
public class I_UserPaymentMethod {

    static UserPaymentMethod userPaymentMethods = new UserPaymentMethod();

    public static RI_UserPaymentMethod getUserPaymentMethodRead() {
        return userPaymentMethods;
    }

    public static WI_UserPaymentMethod getUserPaymentMethodWrite() {
        return userPaymentMethods;
    }
    
}
