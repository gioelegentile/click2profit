package it.clicktoprofit.entity.payments_temp;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface RI_PaymentsTemp {

    ArrayList<ArrayList<String>> getPaymentsTemp() throws SQLException;

}