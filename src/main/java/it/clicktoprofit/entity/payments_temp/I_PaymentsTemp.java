package it.clicktoprofit.entity.payments_temp;

/**
 * Created by Gioele on 07/02/2016.
 */
public class I_PaymentsTemp {

    static PaymentsTemp paymentsTemp = new PaymentsTemp();

    public static RI_PaymentsTemp getPaymentRead() {
        return paymentsTemp;
    }

    public static WI_PaymentsTemp getPaymentWrite() {
        return paymentsTemp;
    }

}
