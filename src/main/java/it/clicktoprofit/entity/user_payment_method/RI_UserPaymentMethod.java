package it.clicktoprofit.entity.user_payment_method;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface RI_UserPaymentMethod {

    ArrayList<ArrayList<String>> getUserPaymentMethods(String id) throws SQLException;

}
