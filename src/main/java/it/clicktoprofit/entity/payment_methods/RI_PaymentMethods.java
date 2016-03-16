package it.clicktoprofit.entity.payment_methods;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface RI_PaymentMethods {

    ArrayList<ArrayList<String>> getPaymentMethods() throws SQLException;

}
