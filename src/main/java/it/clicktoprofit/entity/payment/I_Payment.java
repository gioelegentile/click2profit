package it.clicktoprofit.entity.payment;

/**
 * Created by Gioele on 31/12/2015.
 */
public class I_Payment {
    
    static Payment payment = new Payment();

    public static RI_Payment getPaymentRead() {
        return payment;
    }

    public static WI_Payment getPaymentWrite() {
        return payment;
    }
    
}
