package it.clicktoprofit.entity.payment_methods;

import java.sql.SQLException;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface WI_PaymentMethods {

    boolean setPaymentMethodData(String str);

    void addPaymentMethod() throws SQLException;

    boolean deletePaymentMethod(String id) throws SQLException;

}
