package it.clicktoprofit.entity.payment;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface RI_Payment {

    ArrayList<ArrayList<String>> getUserPayments(String id) throws SQLException;

}
