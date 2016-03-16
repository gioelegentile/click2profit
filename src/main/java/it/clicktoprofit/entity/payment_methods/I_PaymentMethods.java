package it.clicktoprofit.entity.payment_methods;

/**
 * Created by Gioele on 31/12/2015.
 */
public class I_PaymentMethods {

    static PaymentMethods paymentMethods = new PaymentMethods();

    public static RI_PaymentMethods getPaymentMethodsRead() {
        return paymentMethods;
    }

    public static WI_PaymentMethods getPaymentMethodsWrite() {
        return paymentMethods;
    }
    
}
